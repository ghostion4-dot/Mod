package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.tiers;

public class ProgressionTime {
    public static void getTime() {
        int current_day = Toolsarmorprogression.tickCounter/24000; // In game days
        int tier_index = 0;
        String[] next_tier = {"wood", "stone", "copper", "gold", "chainmail", "trident", "mace", "elytra", "iron", "diamond", "netherite"}; // Stores the names, to return to the player
        for (int i = 0; i <= tiers.length; i++) {
            if (current_day < tiers[i]) {
                tier_index = i;
                break;
            }
        int next_tier_day =  tiers[tier_index];
        String next_tier_name = next_tier[tier_index];
        int remaining_days = next_tier_day - current_day;
        int remaining_hours = remaining_days*(1/3);
        int remaining_minutes = remaining_days*20 - (remaining_hours*60);

        }
    }
}
