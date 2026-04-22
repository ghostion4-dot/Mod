package net.mcreator.toolsarmorprogression.procedures;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;


public class BobSetterProcedure {
    public static void setBob(Item item, Player player) {
        ItemStack itemStack = new ItemStack(item);
        int x = player.getInventory().findSlotMatchingItem(itemStack);

        itemStack.set(DataComponents.CUSTOM_NAME, Component.literal("bob"));
        if (player.getInventory().getSlot(x) != null) {
            int count = player.getInventory().countItem(item);
            if (item == Items.PUFFERFISH) itemStack.setCount(Math.min(count, 64));
            System.out.println("bob found");
            Objects.requireNonNull(player.getInventory().getSlot(x)).set(itemStack);
        }
    }
}
