package multiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/25 1:40
 */
public class ZeroEvenOdd {

    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private int print = 1;

    private final Lock lock = new ReentrantLock();
    private final Condition zero = lock.newCondition();
    private final Condition even = lock.newCondition();
    private final Condition odd = lock.newCondition();

    private int state = 0;

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }

            printNumber.accept(0);
            if (print % 2 == 0) {
                state = 2;
            } else {
                state = 1;
            }
        }

    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
        zeroEvenOdd.test();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {
            }
            printNumber.accept(print++);
            state = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
            }
            printNumber.accept(print++);
            state = 0;
        }
    }

    public void test() {


        Thread t2 = new Thread(() -> {
            try {
                even(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.setName("even");
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                odd(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.setName("odd");
        t3.start();

        Thread t1 = new Thread(() -> {
            try {
                zero(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("zero");
        t1.start();
    }

}
