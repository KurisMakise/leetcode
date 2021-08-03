package design.eventbus;

import java.util.concurrent.Executor;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/22 0:29
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(Executor executor) {
        super(executor);
    }

}
