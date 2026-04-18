package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.sounds.SoundEvents;
import java.util.Objects;
import net.minecraft.world.entity.player.Player;
import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.tiers;
import static net.minecraft.util.Mth.floor;

public class ProgressionTime {
    public static int tier_index = 0;
    public static int getTime(CommandSourceStack context) {
        int temp = tier_index;
        int current_day = Toolsarmorprogression.tickCounter/24000; // In game days
        String[] next_tier = {"wood", "stone", "copper", "gold", "chainmail", "trident", "mace", "elytra", "iron", "diamond", "next netherite tool".repeat(6), "all netherite tools"}; // Stores the names, to return to the player
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
        String player = Objects.requireNonNull(context.getPlayer()).getPlainTextName();
        Player playa = Objects.requireNonNull(context.getPlayer());
        String big_message = remaining_days + " day" + day_suffix + " left till " + next_tier_name; // Output days and minutes left
        String small_message = remaining_hours + " hour" + hour_suffix + " & " + remaining_minutes + " minute" + minute_suffix +" left";
        //String mixed_message = remaining_days + " day" + day_suffix + " left till " + next_tier_name + " (" + remaining_hours + " hour" + hour_suffix + " & " + remaining_minutes + " minute" + minute_suffix + ")";
        context.getServer().getCommands().performPrefixedCommand(context.getServer().createCommandSourceStack(), "title " + player + " title " + "\""+ big_message + "\"");
        context.getServer().getCommands().performPrefixedCommand(context.getServer().createCommandSourceStack(), "title " + player + " subtitle " + "\""+ small_message + "\"");
        context.getServer().getCommands().performPrefixedCommand(context.getServer().createCommandSourceStack(), "playsound minecraft:block.amethyst_block.chime player " + player + " ~ ~ ~ 1500");
        return 1;
    }
}
