package io.github.nyg404.command;

import io.github.nyg404.builder.TelegramContext;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommandExecuterImpl {
    private final Map<String, CommandExecuter> commands = new HashMap<>();


    public void register(CommandExecuter executer) {
        String key = executer.prefix() + executer.name();
        commands.put(key, executer);
        log.info("Команда: {} Был успешно зарегестрированна. Префикс: {}", executer.name(), executer.prefix());
    }

    //Я в душе не чаю как это работает, но надеюсь это работает
    public void register(CommandExecuter... executers) {
        for (CommandExecuter executer : executers) {
            register(executer);
        }
    }

    public boolean execute(Update update){
        String text = update.getMessage().getText();
        if (text == null) return false;

        for (Map.Entry<String, CommandExecuter> entry : commands.entrySet()) {
            String key = entry.getKey();
            if (text.startsWith(key)) {
                CommandExecuter executer = entry.getValue();
                TelegramContext ctx = new TelegramContext(update, executer.prefix());
                executer.command(ctx);
                return true;
            }
        }
        return false;
    }

}
