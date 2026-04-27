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
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.*;

public class ItemClampingProcedure {
    static List<String> clampedEnchantments = new ArrayList<>();
    static List<String> bannedEnchantments = new ArrayList<>();

    public static void loadEnchantments() {
        clampedEnchantments.add("unbreaking");
        clampedEnchantments.add("respiration");
        clampedEnchantments.add("smite");
        clampedEnchantments.add("unbreaking");
        clampedEnchantments.add("bane_of_arthropods");
        clampedEnchantments.add("feather_falling");
        clampedEnchantments.add("sweeping_edge");
        clampedEnchantments.add("respiration");
        clampedEnchantments.add("riptide");
        bannedEnchantments.add("protection");
        bannedEnchantments.add("channeling");
        bannedEnchantments.add("density");
        bannedEnchantments.add("fire_aspect");
        bannedEnchantments.add("flame");
        bannedEnchantments.add("fortune");
        bannedEnchantments.add("impaling");
        bannedEnchantments.add("infinity");
        bannedEnchantments.add("knockback");
        bannedEnchantments.add("looting");
        bannedEnchantments.add("loyalty");
        bannedEnchantments.add("mending");
        bannedEnchantments.add("multishot");
        bannedEnchantments.add("power");
        bannedEnchantments.add("punch");
        bannedEnchantments.add("quick_charge");
        bannedEnchantments.add("sharpness");
        bannedEnchantments.add("sweeping_edge");
        bannedEnchantments.add("thorns");
        bannedEnchantments.add("wind_burst");
        bannedEnchantments.add("breach");


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
        ClientboundSetPlayerInventoryPacket packet = new ClientboundSetPlayerInventoryPacket(i, itemStack);
        player.connection.send(packet);


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
        List<ResourceKey<Enchantment>> clamp = getClampedEnchants();
        for (it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<Holder<Enchantment>> enchant : enchants.entrySet()) {
            Optional<ResourceKey<Enchantment>> enchantRegistry = enchant.getKey().unwrapKey();
            if (enchantRegistry.isEmpty()) continue;
            if (enchant.getValue() > 1 && clamp.contains(enchantRegistry.get())) {
                enchant.setValue(1);
            }
        }
    } public static List<ResourceKey<Enchantment>> getClampedEnchants() {
        List<ResourceKey<Enchantment>> newList = new ArrayList<>();
        Registry<Enchantment> enchantmentRegistry = Toolsarmorprogression.myServer.registryAccess().lookupOrThrow(ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("enchantment")));
        for (String enchantmentId : clampedEnchantments) {
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
    } public static List<ResourceKey<Enchantment>> getBannedEnchants() {
        if (Toolsarmorprogression.myServer == null) return new ArrayList<>();

        List<ResourceKey<Enchantment>> newList = new ArrayList<>();

        Registry<Enchantment> enchantmentRegistry = Toolsarmorprogression.myServer.registryAccess().lookupOrThrow(ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("enchantment")));

        for (String enchantmentId : bannedEnchantments) {
            if (!enchantmentId.contains(":")) enchantmentId = "minecraft:" + enchantmentId;

            try {
                var id = Identifier.parse(enchantmentId);
                //? if <= 1.21 {
                /*Enchantment enchantment = enchantmentRegistry.get(id);
                 *///?} else {
                Enchantment enchantment = enchantmentRegistry.getValue(id);
                //?}

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

