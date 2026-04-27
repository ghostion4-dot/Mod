package net.mcreator.toolsarmorprogression.procedures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.diamond;
import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.tickCounter;
import static net.mcreator.toolsarmorprogression.procedures.ItemClampingProcedure.bannedEnchantments;
import static net.mcreator.toolsarmorprogression.procedures.ItemClampingProcedure.clampedEnchantments;
public class RemoveEnchantBanProcedure {
    public static void unbanEnchants() {
        int section = ((tickCounter/24000) - diamond)/30;
        switch (section) {
            case 1:
                String[] unban = {"protection", "channeling", "density", "fortune", "knockback", "looting", "loyalty", "power",
                "quick_charge", "sharpness", "thorns", "wind_burst", "breach"};
                bannedEnchantments.removeAll(List.of(unban));
                String[] clampToLevel1 = {"protection", "density", "fortune", "knockback", "looting", "loyalty", "power",
                        "quick_charge", "sharpness", "thorns", "wind_burst", "breach"};
                String[] clampToLevel2 = {"smite", "bane_of_arthropods", "feather_falling", "fire_protection", "blast_protection",
                "projectile_protection"};
                String[] clampToLevel3 = {};
                String[] clampToLevel4 = {};
                clampTo(1, clampToLevel1);
                clampTo(2, clampToLevel2);
                clampTo(3, clampToLevel3);
                clampTo(4, clampToLevel4);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/home/surreoli/Documents/Ice cold/clamped enchantments.txt"))) {
            for (String clamped : clampedEnchantments) {
                bw.write(clamped);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/home/surreoli/Documents/Ice cold/banned enchantments.txt"))) {
            for (String banned : bannedEnchantments) {
                bw.write(banned);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("can't edit banned txt");
        }

    }
    public static void clampTo(int levelAfter, String[] clamps) {
        try {
            List<String> result = clampedEnchantments;
            for (String clamp : clamps) {
                boolean found = false;
                for (String line : result) {
                    String[] splitLine = line.split(" ");
                    if (splitLine[0].equals(clamp)) {
                        clampedEnchantments.remove(line);
                        clampedEnchantments.add(splitLine[0] + " " + levelAfter);
                        found = true;
                    }
                }
                if (!found) {
                    clampedEnchantments.add(clamp + " " + levelAfter);
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Clamping procedure broke");
        }
    }
}
