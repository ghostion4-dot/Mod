package net.mcreator.toolsarmorprogression.command.manager;

import com.mojang.brigadier.CommandDispatcher;
import net.mcreator.toolsarmorprogression.command.TickIncreaseCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    public static List<Command> commands = new ArrayList<>();

    public static void loadCommands() {
        commands.add(new TickIncreaseCommand());
    }
    public static void registerAllCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, net.minecraft.commands.Commands.CommandSelection registrationEnvironment) {
        if (commands.isEmpty()) {
            loadCommands();
        }
        for (Command command : commands) {
            command.register(dispatcher, commandRegistryAccess);
        }
    }
}