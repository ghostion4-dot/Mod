package net.mcreator.toolsarmorprogression.procedures;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;

public class ElytraProcedure { // Courtesy of Syed Arsam Imam
    public static int LimitElytra(Player player, LevelAccessor world, double x, double y, double z ) {
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
    }
}
