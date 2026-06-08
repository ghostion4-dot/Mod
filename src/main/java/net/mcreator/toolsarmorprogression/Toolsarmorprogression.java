package net.mcreator.toolsarmorprogression;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.mcreator.toolsarmorprogression.command.manager.CommandManager;
import net.mcreator.toolsarmorprogression.procedures.*;import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.TickTask;

import net.mcreator.toolsarmorprogression.init.ToolsArmorProgressionModProcedures;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.EnvType;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandle;

import it.unimi.dsi.fastutil.ints.IntObjectPair;

public class 	Toolsarmorprogression implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger(Toolsarmorprogression.class);
	public static final String MODID = "tools__armor_progression";
	public static final int wood = 0;// In days
	public static final int stone = 1; // In days

	public static final int copper = 4;// In days
	public static final int gold = 8;// In days
	public static final int chainmail = 8;// In days
	public static final int trident = 256;// In days
	public static final int mace = 0;// In days
	public static final int elytra = 0;// In days
	public static final int iron = 32; // In days
	public static final int anvil = 256;
	public static final int diamond = 256; // In days
	public static final int enchantingTable = 256;
	public static final int endCrystal = 256;
	//public static final int netherite = 256; // In days
	public static boolean soundPlayed = false;
	public static int maxNetherite = 0;
	public static boolean soundQueue = false;
	public static final int[] tiers = {wood, stone, copper, gold, chainmail, diamond, mace, elytra, iron, trident, diamond, diamond + 256, diamond + 512, diamond + 768, diamond + 1024, diamond + 1280, diamond + 1536};
	public static int tickCounter = 0;
	public static LevelAccessor myWorld;
	public static boolean tickSet = false;
	public static MinecraftServer myServer;
	public void onInitialize() {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		// Bubble sort for the tiers array
		ItemClampingProcedure.loadBannedEnchantments();
		boolean Sorted = false;
		while (!Sorted) {
			Sorted = true;
			for (int i = 0; i < tiers.length-1; i++) {
				if (tiers[i] > tiers[i+1]) {
					int swap = tiers[i];
					String swap_str = ProgressionTime.next_tier[i];
					String name_str = ProgressionTime.name_color[i];
					tiers[i] = tiers[i+1];
					tiers[i+1] = swap;
					ProgressionTime.next_tier[i] = ProgressionTime.next_tier[i+1];
					ProgressionTime.next_tier[i+1] = swap_str;
					ProgressionTime.name_color[i] = ProgressionTime.name_color[i+1];
					ProgressionTime.name_color[i+1] = name_str;
					Sorted = false;
				}
			}
		}
		if (RemoveItemsProcedure.readTickFromFile()) tickSet = true;
		maxNetherite = Math.max(((tickCounter/24000) - diamond)/256 + 1, 1);
		if (maxNetherite > 6) {
			maxNetherite = 50;
		}
		LOGGER.info("Initializing ToolsArmorProgressionMod");
		CommandRegistrationCallback.EVENT.register(CommandManager::registerAllCommands);
		ToolsArmorProgressionModProcedures.load();
		tick();
		// Start of user code block mod init
		// End of user code block mod init
	}

	// Start of user code block mod methods
	// End of user code block mod methods
	private static final Queue<IntObjectPair<Runnable>> workToBeScheduled = new ConcurrentLinkedQueue<>();
	private static final PriorityQueue<TickTask> workQueue = new PriorityQueue<>(Comparator.comparingInt(TickTask::getTick));
	private void tick() {
		ServerTickEvents.END_SERVER_TICK.register((server) -> {
			if (myServer != server) {
				myServer = server;
			}
			int currentTick = server.getTickCount();
			tickCounter++;
			if ((Toolsarmorprogression.tickCounter/24000) % 5 == 0 & Toolsarmorprogression.tickCounter % 24000 == 1) {
                try {
                    BackupWorldProcedure.BackupWorldFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
			if (Toolsarmorprogression.tickCounter % 24000 == 1) {
				RemoveItemsProcedure.dayPassed(tickCounter/24000, myWorld);
				soundPlayed = false;
			} for (ServerPlayer player : server.getPlayerList().getPlayers()) ElytraProcedure.ElytraDistance(player);
			if (soundQueue) {
				DailySoundProcedure.playSound();
			}
			RemoveItemsProcedure.doPlayersHaveNetherite(maxNetherite, myWorld );
			if (tickCounter%100 == 10) {
				RemoveItemsProcedure.writeTickToFile();

			} if (tickCounter%200 == 100) {PlayerDeathRoleProcedure.playerDeaths();}
			if (tickCounter% 2400== 0) {
				AntiAfkProcedure.DetectAfk();
			}
			IntObjectPair<Runnable> work;
			while ((work = workToBeScheduled.poll()) != null) {
				workQueue.add(new TickTask(currentTick + work.leftInt(), work.right()));
			}

			while (!workQueue.isEmpty() && currentTick >= workQueue.peek().getTick()) {
				Objects.requireNonNull(workQueue.poll()).run();
			}
		});
	}

	private static Object minecraft;
	private static MethodHandle playerHandle;

	@Nullable
	public static Player clientPlayer() {
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			try {
				if (minecraft == null || playerHandle == null) {
					Class<?> minecraftClass = Class.forName("net.minecraft.client.Minecraft");
					minecraft = MethodHandles.publicLookup().findStatic(minecraftClass, "getInstance", MethodType.methodType(minecraftClass)).invoke();
					playerHandle = MethodHandles.publicLookup().findGetter(minecraftClass, "player", Class.forName("net.minecraft.client.player.LocalPlayer"));
				}
				return (Player) playerHandle.invoke(minecraft);
			} catch (Throwable e) {
				LOGGER.error("Failed to get client player", e);
				return null;
			}
		} else {
			return null;
		}
	}
}