package design.eventbus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/22 1:00
 */
public class Test {
    public static void main(String[] args) {
        EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));
        eventBus.register(new TestA());
        eventBus.register(new TestB());
            eventBus.post("你好啊");
            eventBus.post(2222);

    }


    static class TestA {

        @Subscribe
        private void hello(String msg) {
            System.out.println(msg);
        }
    }

    static class TestB {

        @Subscribe
        private void score(Integer score) {
            System.out.println(score);
        }
    }
}
