package net.mcreator.toolsarmorprogression.procedures;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;

import java.util.ArrayList;
import java.util.List;

public class ElytraProcedure { // Courtesy of Syed Arsam Imam
    static List<Boolean> comparePosition = new ArrayList<>();
    static List<String> players = new ArrayList<>();
    static List<Float> playersXPos = new ArrayList<>();
    static List<Float> playersZPos = new ArrayList<>();
    /*public static int LimitElytra(Player player, LevelAccessor world, double x, double y, double z ) {
        if (player == null) return 0;
        if (Toolsarmorprogression.myServer == null) return 0;
        Item bodyItem = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        ItemStack elytraStack = player.getItemBySlot(EquipmentSlot.CHEST);
        int initialD = elytraStack.getDamageValue(); // Elytra before damage
        if (initialD < 432) {
            switch(initialD) {
                case 5:
                    initialD = 108;
                    break;
                case 113:
                    initialD = 216;
                    break;
                case 221:
                    initialD = 324;
                    break;
                case 329:
                    initialD = 432;
                    break;
            }
            System.out.println(initialD);
            elytraStack.setDamageValue(initialD);
        }
        return 1;
    }  */
    public static void ElytraDistance(ServerPlayer player) {
       if (!players.contains(player.getPlainTextName())) {
           players.add(player.getPlainTextName());
           playersZPos.add((float) player.getZ());
           playersXPos.add((float) player.getX());
           comparePosition.add(false);
       } int index = players.indexOf(player.getPlainTextName());
        if (player.isFallFlying()) {
            if (comparePosition.get(index)) {
                float x = playersXPos.get(index);
                float z = playersZPos.get(index);
                float currentX = (float) player.getX();
                float currentZ = (float) player.getZ();
                float deltaX = x - currentX;
                float deltaZ = z - currentZ;
                float delta = (float) Math.sqrt((deltaX * deltaX) + (deltaZ * deltaZ));
                int maxDamage = player.getItemBySlot(EquipmentSlot.CHEST).getMaxDamage();
                int currentDamage = player.getItemBySlot(EquipmentSlot.CHEST).getDamageValue();
                int setDamageTo = currentDamage;
                if (delta > 0.6) {
                    setDamageTo = (int) (currentDamage + delta * 5);
                }playersXPos.set(index, (float) player.getX());
                playersZPos.set(index, (float) player.getZ());
                player.getItemBySlot(EquipmentSlot.CHEST).setDamageValue(Math.min(setDamageTo, maxDamage));
            } else {
                comparePosition.set(index, true);
                playersXPos.set(index, (float) player.getX());
                playersZPos.set(index, (float) player.getZ());
            }
        } else {
            comparePosition.set(index, false);
        }
    }
}
