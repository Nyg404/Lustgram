package io.github.nyg404.builder.impl;


import io.github.nyg404.builder.TelegramContext;
import io.github.nyg404.builder.builder.PhotoBuilder;
import io.github.nyg404.builder.builder.TextBuilder;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;

import java.util.function.Consumer;

public interface BotSender {
    // Полные методы
    void sendText(TelegramContext ctx, String text, Consumer<TextBuilder.TextBuilderBuilder> builder);
    void sendPhoto(@NotNull TelegramContext ctx, InputFile file, Consumer<PhotoBuilder.PhotoBuilderBuilder> builder);
    //Методы без билдера
    void sendText(TelegramContext ctx, String text);
}

