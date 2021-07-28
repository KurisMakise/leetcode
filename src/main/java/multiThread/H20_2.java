package multiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/25 16:31
 */
public class H20_2 {


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        H20_2 h20 = new H20_2();
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


    private Semaphore hSemaphore = new Semaphore(2);
    private Semaphore oSemaphore = new Semaphore(2);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hSemaphore.acquire(1);
        releaseHydrogen.run();
        oSemaphore.release(1);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        oSemaphore.acquire(2);
        releaseOxygen.run();
        hSemaphore.release(2);
    }


}
