package multiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/24 23:33
 */
public class FooBar_1 {
    private int n;

    public FooBar_1(int n) {
        this.n = n;
    }

    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar_1 fooBar = new FooBar_1(5);
        fooBar.test();
    }


    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                countDownLatch.countDown();
                countDownLatch.await();
                foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.countDown();
                countDownLatch.await();
                bar(() -> {
                    System.out.print("bar");
                });
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
        }).start();
    }


}
