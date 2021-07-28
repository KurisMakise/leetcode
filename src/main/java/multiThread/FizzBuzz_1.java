package multiThread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.function.IntConsumer;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/25 18:07
 */
public class FizzBuzz_1 {

    private int n;

    public FizzBuzz_1(int n) {
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
        FizzBuzz_1 fizzBuzz = new FizzBuzz_1(15);
        fizzBuzz.test();
    }

    private CyclicBarrier barrier = new CyclicBarrier(4);

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            try {
                barrier.await();
                if (i % 3 == 0 && i % 5 != 0) {
                    printFizz.run();
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            try {
                barrier.await();
                if (i % 5 == 0 && i % 3 != 0) {
                    printBuzz.run();
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            try {
                barrier.await();
                if (i % 15 == 0 ) {
                    printFizzBuzz.run();
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            try {
                barrier.await();
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
