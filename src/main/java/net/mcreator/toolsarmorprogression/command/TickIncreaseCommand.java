package net.mcreator.toolsarmorprogression.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.mcreator.toolsarmorprogression.command.manager.Command;
import net.mcreator.toolsarmorprogression.procedures.AttributeRandomizerProcedure;
import net.mcreator.toolsarmorprogression.procedures.ProgressionTime;
import net.mcreator.toolsarmorprogression.procedures.RemoveItemsProcedure;
import net.mcreator.toolsarmorprogression.procedures.WorldBorderProcedure;
import net.mcreator.toolsarmorprogression.utils.PermissionManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.List;
import java.util.Objects;

import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.myWorld;
import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.tiers;

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
                        .executes(context -> remainingTick(
                                context.getSource()
                                )

            ));
        dispatcher.register(
                literal("counter")
                        .then(argument("days", StringArgumentType.greedyString())
                                .suggests((context, builder) -> SharedSuggestionProvider.suggest(List.of("ticks","days"), builder))
                                .executes(context -> getCounter(
                                        context.getSource(),
                                        StringArgumentType.getString(context, "days")
                                ))
                        .then(argument("days", StringArgumentType.greedyString())
                                .suggests((context, builder) -> SharedSuggestionProvider.suggest(List.of("ticks","days"), builder))
                                .executes(context -> getCounter(
                                        context.getSource(),
                                        StringArgumentType.getString(context, "days")
                                        )
                                ))));
        dispatcher.register(
                literal("tickReset")
                        .requires(PermissionManager::isAdmin)
                        .executes(context -> resetTick(context.getSource()))
        ); dispatcher.register(literal("unlocked")
                .executes(context -> currentlyUnlocked(
                        context.getSource()
                )));
        dispatcher.register(literal("enable")
                .requires(PermissionManager::isAdmin)
                        .then(literal("attributes")
                .then(argument("enable", StringArgumentType.greedyString())
                        .suggests(((context, builder) -> SharedSuggestionProvider.suggest(List.of("true","false"), builder))
                                ) .executes(context -> randomAttributes(
                                        context.getSource(),
                                StringArgumentType.getString(context, "enable")))))
                .then(literal("worldBorder") .then(argument("enable", StringArgumentType.greedyString())
                        .suggests(((context, builder) -> SharedSuggestionProvider.suggest(List.of("true","false"), builder))
                        ) .executes(context -> enableProgressiveBorder(
                                context.getSource(),
                                StringArgumentType.getString(context, "enable")))))
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
        context.sendSuccess(() -> Component.literal(String.valueOf(Toolsarmorprogression.tickCounter)), true);
        return 1;
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
        RemoveItemsProcedure.timeEndCrystal = false;
        RemoveItemsProcedure.timeEnchantingTable = false;
        RemoveItemsProcedure.timeAnvil = false;
        Toolsarmorprogression.soundPlayed = false;
        context.sendSuccess(() -> Component.literal("reset ticks"), true);
        return 1;
    } public static int remainingTick(CommandSourceStack context) {
           return ProgressionTime.getTime(context);
        }
    public static int currentlyUnlocked(CommandSourceStack context) {
        for (int i = 0; i < tiers.length; i++) {
            if (Toolsarmorprogression.tickCounter / 24000 > tiers[i]) {
                Objects.requireNonNull(context.getPlayer()).sendSystemMessage(Component.literal(ProgressionTime.next_tier[i] + " unlocked"));
            }
        } context.sendSuccess(() -> Component.literal("That is all"), false); return 1;
    } public static int randomAttributes(CommandSourceStack context, String enable) {
        if (enable.equals("true")) {
            AttributeRandomizerProcedure.randomAttributes = true;
            AttributeRandomizerProcedure.attributeRandomizer();
            context.sendSuccess(() -> Component.literal("Random attributes enabled"), false);
        } else {
            AttributeRandomizerProcedure.randomAttributes = false;
            AttributeRandomizerProcedure.resetAttributes();
            context.sendSuccess(() -> Component.literal("Random attributes disable"), false);
        }
        return 1;
    } public static int enableProgressiveBorder(CommandSourceStack context, String enable) {
        if (enable.equals("true")) {
            WorldBorderProcedure.borderOn = true;
            WorldBorderProcedure.increaseBorder();
            context.sendSuccess(() -> Component.literal("Progressive world border enabled!"), true);
        } else {
            WorldBorderProcedure.borderOn = false;
            WorldBorderProcedure.increaseBorder();
            context.sendSuccess(() -> Component.literal("Progressive world border disabled!"), true);
        }
        return 1;
    }
    public static int getCounter(CommandSourceStack context, String isDays) {
        ServerPlayer player = context.getPlayer();
        if (isDays.equals("days")) {
            int dayCount = Toolsarmorprogression.tickCounter/24000;
            ClientboundSetActionBarTextPacket ActionBarTextPacket = new ClientboundSetActionBarTextPacket(Component.literal("§eDay §6"+ dayCount));
            assert player != null;
            ClientboundSoundPacket soundPacket = new ClientboundSoundPacket(SoundEvents.AMBIENT_CAVE, SoundSource.MASTER, player.getX(), player.getY(), player.getZ(), 100.0f, 1.0f, myWorld.getRandom().nextLong());
            player.connection.send(ActionBarTextPacket);
            player.connection.send(soundPacket);
        }
        else if (isDays.equals("ticks")) {
            ClientboundSetActionBarTextPacket ActionBarTextPacket = new ClientboundSetActionBarTextPacket(Component.literal("§eTick §6"+ Toolsarmorprogression.tickCounter));
            assert player != null;
            ClientboundSoundPacket soundPacket = new ClientboundSoundPacket(SoundEvents.AMBIENT_CAVE, SoundSource.MASTER, player.getX(), player.getY(), player.getZ(), 100.0f, 1.0f, myWorld.getRandom().nextLong());
            player.connection.send(ActionBarTextPacket);
            player.connection.send(soundPacket);
        }
        return 0;
    }
}
