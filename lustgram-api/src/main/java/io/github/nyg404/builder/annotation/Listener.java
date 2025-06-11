package io.github.nyg404.builder.annotation;

import io.github.nyg404.builder.Message.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Listener {
    MessageType type() default MessageType.ANY;
    String text() default "";
}
