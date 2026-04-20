package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.client.gui.font.providers.UnihexProvider;
import net.minecraft.world.level.Level;


public class WorldBorderProcedure {
    public static void increaseBorder() {
        Toolsarmorprogression.myServer.getLevel(Level.OVERWORLD).getWorldBorder().setSize(((double) Toolsarmorprogression.tickCounter /24000) * 64 + 64);
        Toolsarmorprogression.myServer.getLevel(Level.NETHER).getWorldBorder().setSize(((double) Toolsarmorprogression.tickCounter /24000) * 8 + 64);
        System.out.println(Toolsarmorprogression.myWorld.getWorldBorder().getSize());
    }
}
