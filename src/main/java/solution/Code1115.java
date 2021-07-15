package solution;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author violet
 * @version 1.0
 * @date 2021/2/28 23:10
 */
public class Code1115 {

    private int n;

    private AtomicInteger flag = new AtomicInteger(0);
    private ReentrantLock lock = new ReentrantLock();
    private Condition fooCondition = lock.newCondition();
    private Condition barCondition = lock.newCondition();

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag.get() != 0) {
                    fooCondition.await();
                }
                flag.getAndIncrement();
                printFoo.run();
                barCondition.signal();
            } finally {
                lock.unlock();
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag.get() != 1) {
                    barCondition.await();
                }
                flag.getAndDecrement();
                printBar.run();
                fooCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
