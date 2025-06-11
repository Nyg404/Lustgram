package io.github.nyg404.listener;

import io.github.nyg404.builder.Message.MessageType;
import io.github.nyg404.builder.TelegramContext;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListenerRegister {

    private final List<ListenerExecute> listenerExecutes = new ArrayList<>();

    public void registerListener(Object listenerObject) {
        for (Method method : listenerObject.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(io.github.nyg404.builder.annotation.Listener.class)) {
                log.info("Метод найден {}", method.getName());
                if (method.getParameterCount() != 1 || method.getParameterTypes()[0] != TelegramContext.class) {
                    throw new IllegalArgumentException("Метод должен принимать 1 параметр TelegramContext");
                }
                method.setAccessible(true);

                io.github.nyg404.builder.annotation.Listener annot = method.getAnnotation(io.github.nyg404.builder.annotation.Listener.class);

                listenerExecutes.add(new ListenerExecute(listenerObject, method, annot));
            }
        }
    }

    public boolean notifyListeners(Update update) throws InvocationTargetException, IllegalAccessException {
        TelegramContext ctx = new TelegramContext(update, ".");
        for (ListenerExecute exec : listenerExecutes) {
            MessageType types = exec.getAnotlistener().type();
            switch (types) {
                case TEXT: {
                    if (update.getMessage() != null
                            && update.getMessage().hasText()
                            && exec.getAnotlistener().text().equals(update.getMessage().getText())) {
                        exec.getMethod().invoke(exec.getObject(), ctx);
                        return true;
                    }
                    break;
                }
                case PHOTO: {
                    if (update.getMessage() != null
                            && update.getMessage().hasPhoto()){
                        exec.getMethod().invoke(exec.getObject(), ctx);
                        return true;
                    }
                }
                break;
                // И т.д позже будет дополняться
            }
        }
        return false;
    }

}
