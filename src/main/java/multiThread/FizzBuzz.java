package multiThread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/25 18:07
 */
public class FizzBuzz {

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }


    public void test() {
        ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MINUTES, new LinkedBlockingDeque<>(),
                new ThreadFactoryBuilder().setNameFormat("线程-%d").build());
        executorService.execute(() -> {
            try {
                fizz(() -> System.out.print("fizz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                buzz(() -> System.out.print("buzz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                fizzbuzz(() -> System.out.print("fizzbuzz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                number((num) -> System.out.print(num));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        fizzBuzz.test();
    }

    private Lock lock = new ReentrantLock();
    private Condition number = lock.newCondition();
    private Condition fizz = lock.newCondition();
    private Condition buzz = lock.newCondition();
    private Condition fizzBuzz = lock.newCondition();

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        for (int i = 3; i <= n ; i += 3) {
            if (i % 5 == 0) {
                continue;
            }

            fizz.await();
            printFizz.run();
            number.signalAll();
        }
        lock.unlock();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            buzz.await();
            printBuzz.run();
            number.signalAll();
        }
        lock.unlock();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        for (int i = 15; i <= n; i += 15) {
            fizzBuzz.await();
            printFizzBuzz.run();
            number.signalAll();
        }
        lock.unlock();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzz.signalAll();
                number.await();
            } else if (i % 3 == 0) {
                fizz.signalAll();
                number.await();
            } else if (i % 5 == 0) {
                buzz.signalAll();
                number.await();
            } else {
                printNumber.accept(i);
            }
        }
        lock.unlock();
    }
}
