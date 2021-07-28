package multiThread;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/24 22:00
 */
public class Foo {

    private final Lock lock = new ReentrantLock();
    private final Condition writeCondition = lock.newCondition();
    private int step = 1;
    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        step = 2;
        lock.lock();
        writeCondition.signalAll();
        lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        while (step != 2) {
            writeCondition.await();
        }

        printSecond.run();
        step = 3;
        writeCondition.signalAll();
        lock.unlock();
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        while (step != 3) {
            writeCondition.await();
        }

        printThird.run();
        lock.unlock();
    }


    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            try {
                countDownLatch.countDown();
                countDownLatch.await();
                first(() -> {
                    System.out.println("first");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.countDown();
                countDownLatch.await();
                second(() -> {
                    System.out.println("second");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.countDown();
                countDownLatch.await();
                third(() -> {
                    System.out.println("third");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        foo.test();
    }

}
