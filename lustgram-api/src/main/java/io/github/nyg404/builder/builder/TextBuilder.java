package io.github.nyg404.builder.builder;

import lombok.Builder;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.LinkPreviewOptions;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.ReplyParameters;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

@Getter
@Builder
public class TextBuilder {
    private long chatId;
    private String text;
    private Integer messageThreadId;
    private String parseMode;
    private Boolean disableWebPagePreview;
    private Boolean disableNotification;
    private Integer replyToMessageId;
    private ReplyKeyboard replyMarkup;
    private List<MessageEntity> entities;
    private Boolean allowSendingWithoutReply;
    private Boolean protectContent;
    private LinkPreviewOptions linkPreviewOptions;
    private ReplyParameters replyParameters;
    private String businessConnectionId;
    private String messageEffectId;
    private Boolean allowPaidBroadcast;

    public SendMessage toSendMessage() {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .messageThreadId(messageThreadId)
                .parseMode(parseMode)
                .disableWebPagePreview(disableWebPagePreview)
                .disableNotification(disableNotification)
                .replyToMessageId(replyToMessageId)
                .replyMarkup(replyMarkup)
                .entities(entities)
                .allowSendingWithoutReply(allowSendingWithoutReply)
                .protectContent(protectContent)
                .linkPreviewOptions(linkPreviewOptions)
                .replyParameters(replyParameters)
                .businessConnectionId(businessConnectionId)
                .messageEffectId(messageEffectId)
                .allowPaidBroadcast(allowPaidBroadcast)
                .build();
    }

}
