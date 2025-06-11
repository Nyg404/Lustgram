package io.github.nyg404.listener;

import lombok.Data;

import java.lang.reflect.Method;
@Data
public class ListenerExecute {
    private final Object object;
    private final Method method;
    private final io.github.nyg404.builder.annotation.Listener anotlistener;

    public ListenerExecute(Object object, Method method, io.github.nyg404.builder.annotation.Listener anotlistener) {
        this.object = object;
        this.method = method;
        this.anotlistener = anotlistener;
    }
}
