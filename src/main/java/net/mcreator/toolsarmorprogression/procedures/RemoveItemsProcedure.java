package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.SleepStatus;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.mcreator.toolsarmorprogression.event.PlayerEvents;
import net.minecraft.world.level.gamerules.GameRules;

import javax.tools.Tool;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RemoveItemsProcedure {
	public static boolean eventResult = true;
	private static boolean timeWood = false;
	private static boolean timeStone = false;
	private static boolean timeCopper = false;
	private static boolean timeGold = false;
	private static boolean timeIron = false;
	private static boolean timeChainmail = false;
	private static boolean timeDiamond = false;
	private static boolean timeNetherite = false;
	private static boolean timeMace = false;
	private static boolean timeElytra = false;
	private static boolean timeTrident = false;
	private static final Item[] stone = new Item[]{Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_SWORD, Items.STONE_HOE, Items.STONE_SHOVEL, Items.STONE_SPEAR};
	private static final Item[] wood = new Item[]{Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_SWORD, Items.WOODEN_HOE, Items.WOODEN_SHOVEL, Items.WOODEN_SPEAR};
	private static final Item[] copper = new Item[]{Items.COPPER_PICKAXE, Items.COPPER_AXE, Items.COPPER_SWORD, Items.COPPER_HOE, Items.COPPER_SHOVEL, Items.COPPER_SPEAR, Items.COPPER_HELMET, Items.COPPER_CHESTPLATE, Items.COPPER_LEGGINGS, Items.COPPER_BOOTS};
	private static final Item[] gold = new Item[]{Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_SWORD, Items.GOLDEN_HOE, Items.GOLDEN_SHOVEL, Items.GOLDEN_SPEAR, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS};
	private static final Item[] iron = new Item[]{Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SWORD, Items.IRON_HOE, Items.IRON_SHOVEL, Items.IRON_SPEAR, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.SHIELD};
	private static final Item[] chainmail = new Item[]{Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS};
	private static final Item[] diamond = new Item[]{Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SWORD, Items.DIAMOND_HOE, Items.DIAMOND_SHOVEL, Items.DIAMOND_SPEAR, Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS};
	private static final Item[] netherite = new Item[]{Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_SWORD, Items.NETHERITE_HOE, Items.NETHERITE_SHOVEL, Items.NETHERITE_SPEAR, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS};
	public RemoveItemsProcedure() {
		PlayerEvents.END_PLAYER_TICK.register(entity -> {
			execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
		});
	}
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (Toolsarmorprogression.server == null) {
			Toolsarmorprogression.server = world.getServer();
		if (entity == null)
			return;
		if (!(entity instanceof Player player)) return;
		if (!Toolsarmorprogression.tickSet) {
			Toolsarmorprogression.tickSet = true;
			if (!readTickFromFile()) {
				Toolsarmorprogression.tickCounter = Math.toIntExact(world.getGameTime());
			}
		}
		if (!timeWood) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.wood) { //wood wood wood WOOD WOOD WOOD

				for (Item item : wood)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);
			} else {
				timeWood = true;
				playSoundAndMessage("Wooden tools", world);
			}
		}if (!timeStone) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.stone) {

				for (Item item : stone)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);

			} else {
				timeStone = true;
				playSoundAndMessage("Stone tools", world);
			}
		}
		if (!timeGold) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.gold) { //GOLD GOLD GOLD GOLD

				for (Item item : gold)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);
			} else {
				timeGold = true;
				playSoundAndMessage("Golden tools", world);
			}
		} if (!timeCopper) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.copper) {

				for (Item item : copper)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);
			} else {
				timeCopper = true;
				playSoundAndMessage("Copper tools", world);
			}
		}if (!timeIron) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.iron) {

				for (Item item : iron)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);
			} else {
				timeIron = true;
				playSoundAndMessage("Iron tools", world);
			}
		}if (!timeChainmail) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.chainmail) {

				for (Item item : chainmail)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);
			} else {
				timeChainmail = true;
				playSoundAndMessage("Chainmail armor", world);
			}
		}if (!timeDiamond) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.diamond) {

				for (Item item : diamond)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);
			} else {
				timeDiamond = true;
				playSoundAndMessage("Diamond tools", world);
			}
		}if (!timeNetherite) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.netherite) {

				for (Item item : netherite)
					if (hasEntityInInventory(player, new ItemStack(item)))
						playerIsNaughty(player, item, world, x, y, z);
			} else {
				timeNetherite = true;
				playSoundAndMessage("Netherite tools", world);
			}
		}if (!timeTrident) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.trident) {

				if (hasEntityInInventory(player, new ItemStack(Items.TRIDENT)))
					playerIsNaughty(player, Items.TRIDENT, world, x, y, z);
			} else {
				timeTrident = true;
				playSoundAndMessage("Trident", world);
			}
		}if (!timeElytra) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.elytra) {

				if (hasEntityInInventory(player, new ItemStack(Items.ELYTRA)))
					playerIsNaughty(player, Items.ELYTRA, world, x, y, z);
			} else {
				timeElytra = true;
				playSoundAndMessage("Elytra", world);
			}
		}if (!timeMace) {
			if ((Toolsarmorprogression.tickCounter / 24000) < Toolsarmorprogression.mace) {

				if (hasEntityInInventory(player, new ItemStack(Items.MACE)))
					playerIsNaughty(player, Items.MACE, world, x, y, z);

			} else {
				timeMace = true;
				playSoundAndMessage("Mace", world);
			}
		}

		} if (world.getServer() != null) {
			int deepSleepers = (int) world.players().stream().filter(Player::isSleepingLongEnough).count();
			int percentage = world.getServer().getGameRules().get(GameRules.PLAYERS_SLEEPING_PERCENTAGE);
			if (deepSleepers >= Math.max(((long) world.players().size() * percentage/100), 1)) {
				System.out.println("There are enough players sleeping");
				Toolsarmorprogression.tickCounter = 24000*((Toolsarmorprogression.tickCounter / 24000) + 1);
			}
		}

	}private static boolean hasEntityInInventory(Player player, ItemStack itemstack) {

		return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
	}
	private static void playerIsNaughty(Player player, Item item, LevelAccessor world, double x, double y, double z) {
		ItemStack itemStack = new ItemStack(item);
		player.getInventory().clearOrCountMatchingItems(p -> itemStack.getItem() == p.getItem(), 1, player.inventoryMenu.getCraftSlots());
		if (world instanceof ServerLevel level) {
			Entity entity = EntityType.LIGHTNING_BOLT.spawn(level, BlockPos.containing(x, y, z), EntitySpawnReason.MOB_SUMMONED);
			if (entity != null) {
				entity.setYRot(world.getRandom().nextFloat() * 360F);
			}
		}

		player.hurt(new DamageSource(world.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.parse("minecraft:bad_respawn_point")))), 1000);
	}
	private static boolean readTickFromFile() {
		try {
			File myObj = new File("tickAmount.txt"); // Create File object
			if (!myObj.createNewFile() && myObj.length()>0) {           // Try to create the file
				Scanner reader = new Scanner(myObj);
				while (reader.hasNextLine()) {
					String ticks = reader.nextLine();
					Toolsarmorprogression.tickCounter = Integer.parseInt(ticks);
					return true;
				}
			} return false;
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace(); // Print error details
		}
        return false;
    } public static void writeTickToFile() {
		try {
			FileWriter myWriter = new FileWriter("tickAmount.txt");
			myWriter.write("" + Toolsarmorprogression.tickCounter);
			myWriter.close();  // must close manually
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	} public static void playSoundAndMessage(String tool, LevelAccessor world) {
		for (Player player : world.players()) {
			player.playSound(SoundEvents.TOTEM_USE);
			Toolsarmorprogression.server.getCommands().performPrefixedCommand(Toolsarmorprogression.server.createCommandSourceStack(), "title " + player + " reset");
			Toolsarmorprogression.server.getCommands().performPrefixedCommand(Toolsarmorprogression.server.createCommandSourceStack(), "title " + player + " title " + "\"New tools unlocked\"");
			player.sendSystemMessage(Component.literal(tool + " has been unlocked!"));
		}
	}
}