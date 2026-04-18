package net.mcreator.toolsarmorprogression.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.mcreator.toolsarmorprogression.command.manager.Command;
import net.mcreator.toolsarmorprogression.command.manager.CommandManager;
import net.mcreator.toolsarmorprogression.procedures.ProgressionTime;
import net.mcreator.toolsarmorprogression.procedures.RemoveItemsProcedure;
import net.mcreator.toolsarmorprogression.utils.PermissionManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Objects;

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
                literal("modtick")
                        .then(literal("set")
                            .requires(PermissionManager::isAdmin)
                            .then(literal("days")
                                .then(argument("amount", StringArgumentType.greedyString())
                                        .executes(context -> setTicks(
                                                context.getSource(),
                                                StringArgumentType.getString(context ,"amount"),
                                                true
                                        ))
                                )

                        ) .then(literal("ticks")
                                .then(argument("amount", StringArgumentType.greedyString())
                                        .executes(context -> setTicks(
                                                context.getSource(),
                                                StringArgumentType.getString(context, "amount"),
                                                false
                                        ))
                                )))
                        .then(literal("add")
                                .requires(PermissionManager::isAdmin)
                                .then(literal("days")
                                        .then(argument("amount", StringArgumentType.greedyString())
                                                .executes(context -> addTicks(
                                                        context.getSource(),
                                                        StringArgumentType.getString(context ,"amount"),
                                                        true
                                                ))
                                        )

                                ) .then(literal("ticks")
                                        .then(argument("amount", StringArgumentType.greedyString())
                                                .executes(context -> addTicks(
                                                        context.getSource(),
                                                        StringArgumentType.getString(context, "amount"),
                                                        false
                                                ))
                                        )))
                        );
        dispatcher.register(
                literal("remainingTime")
                        .then(argument("print", StringArgumentType.greedyString())
                                .suggests((context, builder) -> SharedSuggestionProvider.suggest(List.of("ticks","irl"), builder))
                                        .executes(context -> remainingTick(
                                                context.getSource(),
                                                StringArgumentType.getString(context, "print")
                                        )
                                ))

            );
        dispatcher.register(
                literal("tickReset")
                        .requires(PermissionManager::isAdmin)
                        .executes(context -> resetTick(context.getSource()))
        );
    }
    public static int addTicks(CommandSourceStack context, String x, boolean isDay) {
        if (isDay) {
            Toolsarmorprogression.tickCounter += 24000 * Integer.parseInt(x) - 1;
        } else {
            Toolsarmorprogression.tickCounter += Integer.parseInt(x);
        }
        context.sendSuccess(() -> Component.literal("Successfully added " + Toolsarmorprogression.tickCounter + " ticks to tick counter"), false);
        return 1;
    } public static int getTick(CommandSourceStack context) {
        int x = Toolsarmorprogression.getTick();
        context.sendSuccess(() -> Component.literal(String.valueOf(x)), true);
        return x;
    } public static int setTicks(CommandSourceStack context, String x, boolean isDay) {
        if (isDay) {
            Toolsarmorprogression.tickCounter = 24000 * Integer.parseInt(x) - 1;
        } else {
            Toolsarmorprogression.tickCounter = Integer.parseInt(x);
        }
        context.sendSuccess(() -> Component.literal("Successfully set ticks to " + Toolsarmorprogression.tickCounter), false);
        return 1;
    } public static int resetTick(CommandSourceStack context) {
        RemoveItemsProcedure.timeWood = false;
        RemoveItemsProcedure.timeStone = false;
        RemoveItemsProcedure.timeCopper = false;
        RemoveItemsProcedure.timeGold = false;
        RemoveItemsProcedure.timeIron = false;
        RemoveItemsProcedure.timeChainmail = false;
        RemoveItemsProcedure.timeDiamond = false;
        RemoveItemsProcedure.timeNetherite = false;
        RemoveItemsProcedure.timeMace = false;
        RemoveItemsProcedure.timeElytra = false;
        RemoveItemsProcedure.timeTrident = false;
        context.sendSuccess(() -> Component.literal("reset ticks"), true);
        return 1;
    } public static int remainingTick(CommandSourceStack context, String x) {
        if (Objects.equals(x, "ticks")) {
            return getTick(context);
        } else if(Objects.equals(x, "irl")) {
           return ProgressionTime.getTime(context);
        } return -1;
    }
}
