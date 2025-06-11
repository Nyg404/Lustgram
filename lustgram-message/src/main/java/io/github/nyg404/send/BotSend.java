package io.github.nyg404.send;


import io.github.nyg404.builder.TelegramContext;
import io.github.nyg404.builder.builder.PhotoBuilder;
import io.github.nyg404.builder.builder.TextBuilder;
import io.github.nyg404.builder.impl.BotSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.function.Consumer;

@Slf4j
@AllArgsConstructor
public class BotSend implements BotSender {
    private final TelegramClient client;

    @Override
    public void sendText(@NotNull TelegramContext ctx, String text, Consumer<TextBuilder.TextBuilderBuilder> builder) {
        try {
            TextBuilder.TextBuilderBuilder builders = TextBuilder.builder()
                    .chatId(ctx.getChatId())
                    .text(text);

            if (builder != null) {
                builder.accept(builders);
            }

            SendMessage msg = builders.build().toSendMessage();

            client.executeAsync(msg)
                    .exceptionally(e -> {
                        log.error("Ошибка при отправке текстового сообщения (асинхронно): ", e);
                        return null;
                    });
        } catch (Exception e) {
            log.error("Ошибка при подготовке или отправке текстового сообщения: ", e);
        }
    }

    @Override
    public void sendPhoto(@NotNull TelegramContext ctx, InputFile file, Consumer<PhotoBuilder.PhotoBuilderBuilder> builder){
        try {
            PhotoBuilder.PhotoBuilderBuilder builders = PhotoBuilder.builder()
                    .chatId(String.valueOf(ctx.getChatId()))
                    .photo(file);
            if (builder != null) {
                builder.accept(builders);
            }
            SendPhoto msg = builders.build().toSendPhoto();

            client.executeAsync(msg)
                    .exceptionally(e -> {
                        log.error("Ошибка при отправке фотографии (асинхронно): ", e);
                        return null;
                    });
        } catch (Exception e) {
            log.error("Ошибка при подготовке или отправке текстового сообщения: ", e);
        }

    }

    @Override
    public void sendText(@NotNull TelegramContext ctx, String text) {
        sendText(ctx, text, null);
    }

}
