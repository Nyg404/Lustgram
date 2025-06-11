package io.github.nyg404;


import io.github.nyg404.command.CommandExecuter;
import io.github.nyg404.command.CommandExecuterImpl;
import io.github.nyg404.listener.ListenerRegister;
import io.github.nyg404.send.BotSend;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public abstract class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
     private final CommandExecuterImpl commandExecuter = new CommandExecuterImpl();
     private final @Getter TelegramClient client;
     private final String token;
     private final @Getter BotSend botSend;
     private final ListenerRegister listenerRegister = new ListenerRegister();

     public TelegramBot(String token)  {
         this.token = token;
         client = new OkHttpTelegramClient(token);
         botSend = new BotSend(client);

     }

    /**
     * Запуск бота.
     */
    public void register(){
         try {
             TelegramBotsLongPollingApplication telegramBotsLongPollingApplication = new TelegramBotsLongPollingApplication();
             telegramBotsLongPollingApplication.registerBot(token, this);
             log.info("Бот успешно инициализирован.");
         } catch (TelegramApiException e){
             log.error("Ошибка при инициализации бота: ", e);
         }
    }

    /**
     * Отключёние бота.
     */
    public void unRegister(){
        try {
            TelegramBotsLongPollingApplication telegramBotsLongPollingApplication = new TelegramBotsLongPollingApplication();
            telegramBotsLongPollingApplication.unregisterBot(token);
            log.info("Бот успешно отключён.");
        } catch (TelegramApiException e){
            log.error("Ошибка при отключении бота: ", e);
        }
    }

    public void registerListener(Object listener) {
        listenerRegister.registerListener(listener);
    }


    public CommandExecuterImpl commandExecuter() {
        return commandExecuter;
    }

    public void registerCommand(CommandExecuter commandExecuters){
        commandExecuter.register(commandExecuters);
    }

    @Override
    public void consume(Update update) {
        commandExecuter.execute(update);
        try {
            listenerRegister.notifyListeners(update);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
