package multiThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/24 23:33
 */
public class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    private final Lock lock = new ReentrantLock();
    private final Condition fooCondition = lock.newCondition();
    private final Condition barCondition = lock.newCondition();
    private int step = 1;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            while (step != 1) {
                fooCondition.await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            step = 2;
            barCondition.signalAll();
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            while (step != 2) {
                barCondition.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            step = 1;
            fooCondition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(6);
        fooBar.test();
    }

    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                countDownLatch.countDown();
                countDownLatch.await();
                foo(() -> {
                    System.out.println("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.countDown();
                countDownLatch.await();
                bar(() -> {
                    System.out.println("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
