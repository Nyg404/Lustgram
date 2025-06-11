package io.github.nyg404.builder.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.ReplyParameters;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

@Getter
@Builder
public class PhotoBuilder {
    private @NonNull String chatId;
    private Integer messageThreadId;
    private @NonNull InputFile photo;
    private String caption;
    private Boolean disableNotification;
    private Integer replyToMessageId;
    private ReplyKeyboard replyMarkup;
    private String parseMode;
    private List<MessageEntity> captionEntities;
    private Boolean allowSendingWithoutReply;
    private Boolean protectContent;
    private Boolean hasSpoiler;
    private ReplyParameters replyParameters;
    private String businessConnectionId;
    private String messageEffectId;
    private Boolean showCaptionAboveMedia;
    private Boolean allowPaidBroadcast;

    public SendPhoto toSendPhoto(){
        return SendPhoto.builder()
                .chatId(chatId)
                .messageThreadId(messageThreadId)
                .photo(photo)
                .caption(caption)
                .disableNotification(disableNotification)
                .replyToMessageId(replyToMessageId)
                .replyMarkup(replyMarkup)
                .parseMode(parseMode)
                .captionEntities(captionEntities)
                .allowSendingWithoutReply(allowSendingWithoutReply)
                .protectContent(protectContent)
                .hasSpoiler(hasSpoiler)
                .replyParameters(replyParameters)
                .businessConnectionId(businessConnectionId)
                .messageEffectId(messageEffectId)
                .showCaptionAboveMedia(showCaptionAboveMedia)
                .allowPaidBroadcast(allowPaidBroadcast)
                .build();
    }
}
