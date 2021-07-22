package design.eventbus;

import com.google.common.base.Preconditions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/22 0:29
 */
public class ObserverRegistry {

    private final List<Object> observers = new ArrayList<>();

    public void register(Object object) {
        observers.add(object);
    }

    public List<ObserverAction> getMatchedObserverActions(Object event) {
        List<ObserverAction> observerActions = new ArrayList<>();
        for (Object observer : observers) {
            List<Method> annotatedMethods = getAnnotatedMethods(observer.getClass());
            annotatedMethods.forEach(method -> {
                Class<?> parameterType = method.getParameterTypes()[0];
                if (event.getClass() == parameterType) {
                    observerActions.add(new ObserverAction(observer, method));
                }
            });
        }

        return observerActions;
    }

    public List<Method> getAnnotatedMethods(Class<?> clazz) {
        List<Method> annotatedMethods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();

                Preconditions.checkArgument(parameterTypes.length == 1, "参数必须为1", method, parameterTypes.length);
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

}

