package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.commands.CommandSourceStack;

import java.util.Objects;

import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.tiers;

public class ProgressionTime {
    public static int tier_index = 0;
    public static int getTime(CommandSourceStack context) {
        int current_day = Toolsarmorprogression.tickCounter/24000; // In game days
        String[] next_tier = {"wood", "stone", "copper", "gold", "chainmail", "trident", "mace", "elytra", "iron", "diamond", "netherite"}; // Stores the names, to return to the player
        for (int i=0; i < tiers.length; i++) {
            if (current_day < tiers[i]) {
                tier_index = i;
                break;
            }
        int next_tier_day =  tiers[tier_index];
        String next_tier_name = next_tier[tier_index];
        int remaining_days = next_tier_day - current_day;
        int remaining_hours = remaining_days/3;
        int remaining_minutes = remaining_days*20 - (remaining_hours*60);
        String player = Objects.requireNonNull(context.getPlayer()).getPlainTextName();
        String big_message = remaining_days + " days left till " + next_tier_name;
        String small_message = remaining_hours + " hours and " + remaining_minutes + " minutes left";
        System.out.println(big_message);
        System.out.println(small_message);
        context.getServer().getCommands().performPrefixedCommand(context.getServer().createCommandSourceStack(), "title " + player + " title " + "\""+ big_message + "\"");
        context.getServer().getCommands().performPrefixedCommand(context.getServer().createCommandSourceStack(), "title " + player + " subtitle " + "\""+ small_message + "\"");

        }
        return 1;
    }
}
