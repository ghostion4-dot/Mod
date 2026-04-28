package net.mcreator.toolsarmorprogression.procedures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.diamond;
import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.tickCounter;
import static net.mcreator.toolsarmorprogression.procedures.ItemClampingProcedure.bannedEnchantments;
import static net.mcreator.toolsarmorprogression.procedures.ItemClampingProcedure.clampedEnchantments;
public class RemoveEnchantBanProcedure {
    static List<String> temp = new ArrayList<>();
    static String[] unban;
    static String[] clampToLevel1;
    static String[] clampToLevel2;
    static String[] clampToLevel3;
    static String[] clampToLevel4;
    static String[] clampToLevel5;
    static String[] unclamp;
    public static void unbanEnchants() {
        int section = ((tickCounter/24000) - diamond)/30;
        switch (section) {
            case 1:
                unban = new String[]{"protection", "channeling", "density", "fortune", "knockback", "looting", "loyalty", "power",
                        "quick_charge", "sharpness", "thorns", "wind_burst", "breach"};
                bannedEnchantments.removeAll(List.of(unban));
                clampToLevel1 = new String[]{"protection", "density", "fortune", "knockback", "looting", "loyalty", "power",
                        "quick_charge", "sharpness", "thorns", "wind_burst", "breach"};
                clampToLevel2 = new String[]{"smite", "bane_of_arthropods", "feather_falling", "fire_protection", "blast_protection",
                        "projectile_protection"};
                clampToLevel3 = new String[]{};
                clampToLevel4 = new String[]{};
                clampTo(1, clampToLevel1);
                clampTo(2, clampToLevel2);
                break;
            case 2:
                unban = new String[]{"sweeping_edge", "punch", "impaling"};
                bannedEnchantments.removeAll(List.of(unban));
                clampToLevel1 = new String[]{"sweeping_edge", "punch"};
                clampToLevel2 = new String[]{"sharpness", "power", "breach", "wind_burst", "density", "protection", "loyalty"};
                clampToLevel3 = new String[]{"smite", "bane_of_arthropods", "efficiency", "fire_protection", "blast_protection",
                        "projectile_protection", "feather_falling"};
                clampTo(1, clampToLevel1);
                clampTo(2, clampToLevel2);
                clampTo(3, clampToLevel3);
                break;
            case 3:
                unban = new String[]{"fire_aspect", "multishot", "flame"};
                bannedEnchantments.removeAll(List.of(unban));
                clampToLevel1 = new String[]{"fire_aspect"};
                clampToLevel2 = new String[]{"fortune", "looting", "quick_charge", "thorns", "knockback", "wind_burst"};
                clampToLevel3 = new String[]{"loyalty", "breach", "density"};
                clampToLevel4 = new String[]{"smite", "bane_of_arthropods"};
                clampTo(1, clampToLevel1);
                clampTo(2, clampToLevel2);
                clampTo(3, clampToLevel3);
                clampTo(4, clampToLevel4);
                break;
            case 4:
                unban = new String[]{"infinity"};
                bannedEnchantments.removeAll(List.of(unban));

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
            List<String> result = new ArrayList<>();
            List<String> level = new ArrayList<>();
            for (String line : clampedEnchantments) {
                result.add(line.split(" ")[0]);
                level.add(line.split(" ")[1]);
            }
            for (String enchantment : clamps) {
                if (result.contains(enchantment)) {
                    int x = result.indexOf(enchantment);
                    level.set(x, String.valueOf(levelAfter));
                } else {
                    result.add(enchantment);
                    level.add(String.valueOf(levelAfter));
                }
            }
            clampedEnchantments.clear();
            for (String i : result) {
                i = i + " " + level.get(result.indexOf(i));
                clampedEnchantments.add(i);
            } System.out.println(clampedEnchantments);
        }catch (RuntimeException e) {
            System.out.println("Clamping procedure broke");
        }
    }
}
