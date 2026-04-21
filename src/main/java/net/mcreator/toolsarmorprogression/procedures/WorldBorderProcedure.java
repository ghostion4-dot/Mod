package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.world.level.Level;


public class WorldBorderProcedure {
    public static boolean borderOn;
    public static void increaseBorder() {
        if (borderOn) {
            Toolsarmorprogression.myServer.getLevel(Level.OVERWORLD).getWorldBorder().setSize(((double) Toolsarmorprogression.tickCounter /24000) * 64 + 64);
            Toolsarmorprogression.myServer.getLevel(Level.NETHER).getWorldBorder().setSize(((double) Toolsarmorprogression.tickCounter /24000) * 8 + 64);
            System.out.println(Toolsarmorprogression.myWorld.getWorldBorder().getSize());
        } else {
            Toolsarmorprogression.myServer.getLevel(Level.OVERWORLD).getWorldBorder().setSize(59999968);
            Toolsarmorprogression.myServer.getLevel(Level.NETHER).getWorldBorder().setSize(59999968);
            Toolsarmorprogression.myServer.getLevel(Level.END).getWorldBorder().setSize(59999968);
        }
    }
}
