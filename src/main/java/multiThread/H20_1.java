package multiThread;

import java.util.Locale;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/25 16:31
 */
public class H20_1 {


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        H20_1 h20 = new H20_1();
        h20.test();
    }

    public void test() {
        char[] array = "OHHOOOHOHHHHHHH".toCharArray();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'H') {
                new Thread(() -> {
                    try {
                        hydrogen(() -> System.out.print("H"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        oxygen(() -> System.out.print("O"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }


    }


    private Lock lock = new ReentrantLock();
    private Condition hydrogen = lock.newCondition();
    private Condition oxygen = lock.newCondition();

    private int oState = 1;
    private int hState = 2;

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        lock.lock();
        if (hState == 0) {
            hydrogen.await();
        }
        releaseHydrogen.run();
        hState--;
        if (hState == 0 && oState == 0) {
            hState = 2;
            oState = 1;
            oxygen.signal();
        }
        lock.unlock();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        lock.lock();
        if (oState == 0) {
            oxygen.await();
        }
        releaseOxygen.run();
        oState--;
        if (hState == 0 && oState == 0) {
            hState = 2;
            oState = 1;
            hydrogen.signal();
        }
        lock.unlock();
    }


}
