package io.github.nyg404.builder;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Slf4j
@Getter
public class TelegramContext {
    private final Long userId;

    private final Long chatId;

    private final String prefix;

    private final Update update;

    private final Message message;

    private final String[] args;

    private final Chat chat;

    private final User user;



    public TelegramContext(Update update, String prefix) {
        this.prefix = prefix;
        this.update = update;

        this.message = update.getMessage();
        String text = message.getText();
        if (text == null || text.isBlank()) {
            this.args = new String[0];
        } else {
            this.args = text.trim().split("\\s+");
        }

        this.chat = message.getChat();
        this.user = message.getFrom();
        this.userId = message.getFrom().getId();
        this.chatId = message.getChatId();
    }
}
