package net.mcreator.toolsarmorprogression.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.mcreator.toolsarmorprogression.command.manager.Command;
import net.mcreator.toolsarmorprogression.utils.PermissionManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class TickIncreaseCommand extends Command {
    @Override
    public boolean isAllowed() {
        return true;
    }

    @Override
    public Component getBannedText() {
        return Component.nullToEmpty("This command is only available when you have selected a Season.");
    }

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("addTicks")
                        .requires(PermissionManager::isAdmin)
                        .then(argument("message", StringArgumentType.greedyString())
                                .executes(context -> addTicks(
                                        context.getSource(),
                                        StringArgumentType.getString(context ,"message")
                                ))
                        )
        ); dispatcher.register(
                literal("getTicks")
                        .executes(context -> getTick(context.getSource()))
        );dispatcher.register(
                literal("setTicks")
                        .requires(PermissionManager::isAdmin)
                        .then(argument("message", StringArgumentType.greedyString())
                                .executes(context -> setTicks(
                                        context.getSource(),
                                        StringArgumentType.getString(context ,"message")
                                ))
                        )
        );
    }
    public static int addTicks(CommandSourceStack context, String x) {
        Toolsarmorprogression.LOGGER.debug(x);
        Toolsarmorprogression.increaseTick(Integer.parseInt(x));
        context.sendSuccess(() -> Component.nullToEmpty("Successfully added " + x + " ticks"), true);
        return 1;
    } public static int getTick(CommandSourceStack context) {
        int x = Toolsarmorprogression.getTick();
        context.sendSuccess(() -> Component.literal(String.valueOf(x)), true);
        return x;
    } public static int setTicks(CommandSourceStack context, String x) {
        Toolsarmorprogression.tickCounter = Integer.parseInt(x);
        context.sendSuccess(() -> Component.nullToEmpty("Successfully set ticks to " + x), true);
        return 1;
    }
}
