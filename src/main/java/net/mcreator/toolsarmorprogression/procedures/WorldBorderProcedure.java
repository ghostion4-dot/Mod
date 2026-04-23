package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.world.level.Level;

import java.util.Objects;


public class WorldBorderProcedure {
    public static boolean borderOn;
    public static void increaseBorder() {
        if (borderOn) {
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.OVERWORLD)).getWorldBorder().setSize(((double) Toolsarmorprogression.tickCounter /24000) * 128 + 128);
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.NETHER)).getWorldBorder().setSize(((double) Toolsarmorprogression.tickCounter /24000) * 32 + 32);
            System.out.println(Toolsarmorprogression.myWorld.getWorldBorder().getSize());
        } else {
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.OVERWORLD)).getWorldBorder().setSize(59999968);
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.NETHER)).getWorldBorder().setSize(59999968);
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.END)).getWorldBorder().setSize(59999968);
        }
    }
}
