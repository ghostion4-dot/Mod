package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.awt.*;

import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.myWorld;
import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.tiers;
import static net.minecraft.util.Mth.floor;

public class ProgressionTime {
    public static int tier_index = 0;
    public static String[] next_tier = {"wood", "stone", "copper", "gold", "chainmail",  "diamond", "mace", "elytra", "iron", "trident", "next netherite tool", "next netherite tool", "next netherite tool", "next netherite tool", "next netherite tool", "next netherite tool", "all netherite tools"}; // Stores the names, to return to the player
    public static int getTime(CommandSourceStack context) {
        int current_day = Toolsarmorprogression.tickCounter/24000; // In game days
        for (int i=0; i < tiers.length; i++) {
            if (current_day < tiers[i]) {
                tier_index = i;
                break;
            }
        }
        int next_tier_day =  tiers[tier_index]; // Calculate the exact appropriate times
        String next_tier_name = next_tier[tier_index];
        int remaining_days = next_tier_day - current_day;
        int remaining_ticks = 24000*next_tier_day - Toolsarmorprogression.tickCounter;
        int remaining_hours = floor((float) remaining_ticks /72000);
        int remaining_minutes = remaining_ticks/1200 - (remaining_hours*60);
        String day_suffix = "s"; // Plural suffixes so the grammar for the message is correct
        String hour_suffix = "s";
        String minute_suffix = "s";
        if (remaining_days == 1) {
            day_suffix = "";
        }
        if (remaining_hours == 1) {
            hour_suffix = "";
        }
        if (remaining_minutes == 1) {
            minute_suffix = "";
        }
        ServerPlayer player = context.getPlayer();
        ClientboundSetTitleTextPacket titlePacket = new ClientboundSetTitleTextPacket(Component.literal(remaining_days + " day" + day_suffix + " left till " + next_tier_name));
        ClientboundSetSubtitleTextPacket subtitleTextPacket = new ClientboundSetSubtitleTextPacket(Component.literal(remaining_hours + " hour" + hour_suffix + " & " + remaining_minutes + " minute" + minute_suffix +" left"));
        assert player != null;
        //String mixed_message = remaining_days + " day" + day_suffix + " left till " + next_tier_name + " (" + remaining_hours + " hour" + hour_suffix + " & " + remaining_minutes + " minute" + minute_suffix + ")";
        ClientboundSoundPacket soundPacket = new ClientboundSoundPacket(Holder.direct(SoundEvents.AMETHYST_BLOCK_CHIME), SoundSource.MASTER, player.getX(), player.getY(), player.getZ(), 1.0f, 1.0f, myWorld.getRandom().nextLong());
        player.connection.send(titlePacket);
        player.connection.send(subtitleTextPacket);
        player.connection.send(soundPacket);
        return 1;
    }
}
