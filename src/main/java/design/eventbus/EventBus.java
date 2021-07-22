package design.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/22 0:22
 */
public class EventBus {
    private final Executor executor;

    private final ObserverRegistry observerRegistry = new ObserverRegistry();


    public EventBus() {
        executor = MoreExecutors.directExecutor();
    }

    public EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object) {
        observerRegistry.register(object);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = observerRegistry.getMatchedObserverActions(event);

        observerActions.forEach(observerAction -> executor.execute(() -> observerAction.execute(event)));
    }


}
