package io.github.nyg404.command;

import io.github.nyg404.builder.TelegramContext;

public interface CommandExecuter {
    String prefix();
    String name();
    void command(TelegramContext ctx);
}
