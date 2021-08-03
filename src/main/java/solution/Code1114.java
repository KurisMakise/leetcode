package solution;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author violet
 * @version 1.0
 * @date 2021/2/28 22:52
 */
public class Code1114 {

    private final AtomicInteger signal = new AtomicInteger(1);


    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        signal.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (signal.get() != 2) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        signal.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (signal.get() != 3) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
