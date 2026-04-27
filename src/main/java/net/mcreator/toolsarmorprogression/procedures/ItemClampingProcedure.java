package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.game.ClientboundSetPlayerInventoryPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ItemClampingProcedure {
    public static List<String> clampedEnchantments = new ArrayList<>();
    public static List<String> bannedEnchantments = new ArrayList<>();

    public static void loadBannedEnchantments() {
        File file = new File("/home/surreoli/Documents/Ice cold/banned enchantments.txt");
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                bannedEnchantments.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
    public static ItemEnchantments clampAndBlacklistEnchantments(ItemEnchantments enchants) {
        ItemEnchantments afterBlacklist = blacklistEnchantments(enchants);
        clampEnchantments(afterBlacklist);
        return afterBlacklist;
    }
    public static void processItemStack(ServerPlayer player, ItemStack itemStack, int i) {
        if (player.isCreative()) return;
        if (itemStack.isEmpty()) return;
        if (itemStack.getItem() == Items.AIR) return;

        ItemEnchantments enchants = itemStack.getComponents().get(DataComponents.ENCHANTMENTS);
        ItemEnchantments enchantsStored = itemStack.getComponents().get(DataComponents.STORED_ENCHANTMENTS);
        if (enchants != null) {
            itemStack.set(DataComponents.ENCHANTMENTS, clampAndBlacklistEnchantments(enchants));
        }
        if (enchantsStored != null) {
            itemStack.set(DataComponents.STORED_ENCHANTMENTS, clampAndBlacklistEnchantments(enchantsStored));
        }


    } public static ItemEnchantments blacklistEnchantments(ItemEnchantments enchants) {
        if (enchants.isEmpty()) return enchants;
        List<ResourceKey<Enchantment>> banned = getBannedEnchants();
        if (banned.isEmpty()) return enchants;
        List<it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<Holder<Enchantment>>> toRemove = new ArrayList<>();
        for (it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<Holder<Enchantment>> enchant : enchants.entrySet()) {
            Optional<ResourceKey<Enchantment>> enchantRegistry = enchant.getKey().unwrapKey();
            if (enchantRegistry.isEmpty()) continue;
            if (banned.contains(enchantRegistry.get())) {
                toRemove.add(enchant);
            }
        }
        if (toRemove.isEmpty()) return enchants;
        ItemEnchantments.Mutable builder = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);

        for (it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<Holder<Enchantment>> enchant : enchants.entrySet()) {
            if (toRemove.contains(enchant)) continue;
            builder.upgrade(enchant.getKey(), enchant.getIntValue());

        }

        return builder.toImmutable();
    }
    public static void clampEnchantments(ItemEnchantments enchants) {
        var clamped = getClampedEnchants();
        for (int level = 1; level <= 4; level++) {
            List<ResourceKey<Enchantment>> clamp = clamped.get(level);
            if (clamp == null) continue;
            for (it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<Holder<Enchantment>> enchant : enchants.entrySet()) {
                Optional<ResourceKey<Enchantment>> enchantRegistry = enchant.getKey().unwrapKey();
                if (enchantRegistry.isEmpty()) continue;
                if (enchant.getValue() > level && clamp.contains(enchantRegistry.get())) {
                    enchant.setValue(level);
                }
            }
        }
    } public static Map<Integer, List<ResourceKey<Enchantment>>> getClampedEnchants() {
        Map<Integer, List<ResourceKey<Enchantment>>> newList = new HashMap<>();
        newList.put(1, new ArrayList<>());
        newList.put(2, new ArrayList<>());
        newList.put(3, new ArrayList<>());
        newList.put(4, new ArrayList<>());
        if (Toolsarmorprogression.myServer == null) {
            return newList;
        }
        Map<Integer, List<String>> loadedRaw = loadClampedEnchants();
        Registry<Enchantment> enchantmentRegistry = Toolsarmorprogression.myServer.registryAccess().lookupOrThrow(ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("enchantment")));
        for (int level = 1; level <= 4; level++) {
            List<String> enchants = loadedRaw.get(level);
            if (enchants == null) continue;
            for (String enchantmentId : enchants) {
                if (!enchantmentId.contains(":")) enchantmentId = "minecraft:" + enchantmentId;
                try {
                    var id = Identifier.parse(enchantmentId);
                    Enchantment enchantment = enchantmentRegistry.getValue(id);

                    if (enchantment != null) {
                        newList.get(level).add(enchantmentRegistry.getResourceKey(enchantment).orElseThrow());
                    } else {
                        System.out.println("[CONFIG] Invalid enchantment: " + enchantmentId);
                    }
                } catch (Exception e) {
                    System.out.println("[CONFIG] Error parsing enchantment ID: " + enchantmentId);
                }
            }
        }

        return newList;
    }

    public static Map<Integer, List<String>> loadClampedEnchants() {
        Map<Integer, List<String>> result = new HashMap<>();
        result.put(1, new ArrayList<>());
        result.put(2, new ArrayList<>());
        result.put(3, new ArrayList<>());
        result.put(4, new ArrayList<>());
        if (clampedEnchantments.isEmpty()) {
            File file = new File("/home/surreoli/Documents/Ice cold/clamped enchantments.txt");
            try (Scanner myReader = new Scanner(file)) {
                while (myReader.hasNextLine()) {
                    clampedEnchantments.add(myReader.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } for (String line : clampedEnchantments) {
            String[] splitLine = line.split(" ");
            result.get(Integer.parseInt(splitLine[1])).add(splitLine[0]);
        }
        return result;
    }

    public static List<ResourceKey<Enchantment>> getBannedEnchants() {
        if (Toolsarmorprogression.myServer == null) return new ArrayList<>();

        List<ResourceKey<Enchantment>> newList = new ArrayList<>();

        Registry<Enchantment> enchantmentRegistry = Toolsarmorprogression.myServer.registryAccess().lookupOrThrow(ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("enchantment")));

        for (String enchantmentId : bannedEnchantments) {
            if (!enchantmentId.contains(":")) enchantmentId = "minecraft:" + enchantmentId;

            try {
                var id = Identifier.parse(enchantmentId);
                Enchantment enchantment = enchantmentRegistry.getValue(id);
                if (enchantment != null) {
                    newList.add(enchantmentRegistry.getResourceKey(enchantment).orElseThrow());
                } else {
                    System.out.println("[CONFIG] Invalid enchantment: " + enchantmentId);
                }
            } catch (Exception e) {
                System.out.println("[CONFIG] Error parsing enchantment ID: " + enchantmentId);
            }
        }
        return newList;
    }
}

