package design.eventbus;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/22 1:00
 */
public class Test {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
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
        private void score(int score) {
            System.out.println(score);
        }
    }
}
