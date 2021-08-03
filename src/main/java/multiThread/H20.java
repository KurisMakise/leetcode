package multiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/25 16:31
 */
public class H20 {
    private String s = "OOHHHH";

    private int n = 4;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        H20 h20 = new H20();

        h20.print();
    }



    private void print() throws BrokenBarrierException, InterruptedException {

        new Thread(() -> {
            try {
                hydrogen(() -> System.out.print("H"));
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                hydrogen(() -> System.out.print("H"));
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                oxygen(() -> System.out.print("O"));
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
        n--;            System.out.println();

    });

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException, BrokenBarrierException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        while (n > 0) {
            releaseHydrogen.run();
            cyclicBarrier.await();

        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException, BrokenBarrierException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        while (n > 0) {
            releaseOxygen.run();
            cyclicBarrier.await();

        }
    }


}
