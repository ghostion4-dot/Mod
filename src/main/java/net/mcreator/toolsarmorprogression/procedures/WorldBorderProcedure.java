package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.world.level.Level;

import java.util.Objects;


public class WorldBorderProcedure {
    public static boolean borderOn;
    public static void increaseBorder() {
        if (borderOn) {
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.OVERWORLD)).getWorldBorder().setSize(Math.min(((double) Toolsarmorprogression.tickCounter /24000) * 32 + 1028, 12000));
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.NETHER)).getWorldBorder().setSize(Math.min(((double) Toolsarmorprogression.tickCounter /24000) * 16 + 128, 1500));
            System.out.println(Toolsarmorprogression.myWorld.getWorldBorder().getSize());
        } else {
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.OVERWORLD)).getWorldBorder().setSize(59999968);
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.NETHER)).getWorldBorder().setSize(59999968);
            Objects.requireNonNull(Toolsarmorprogression.myServer.getLevel(Level.END)).getWorldBorder().setSize(59999968);
        }
    }
}
