package net.mcreator.toolsarmorprogression.procedures;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec2;

import javax.swing.plaf.synth.SynthUI;
import java.util.HashMap;
import java.util.Map;

public class AntiAfkProcedure {
     static Map<String, Vec2> playerAngles = new HashMap<>();

    public static void DetectAfk() {
        try {
            for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) {// Store every old player head rotation vector
                Vec2 currentPlayerLookAngle = player.getRotationVector();
                if (playerAngles.containsKey(player.getPlainTextName())) {
                    if (playerAngles.get(player.getPlainTextName()).x == currentPlayerLookAngle.x && currentPlayerLookAngle.y == playerAngles.get(player.getPlainTextName()).y) { // If player in array, kick
                        System.out.println(player.getPlainTextName() + " is afk. Kicking.");
                        player.connection.disconnect(Component.literal("fatass"));
                    } else { // or don't
                        playerAngles.put(player.getPlainTextName(), currentPlayerLookAngle); // If player has moved, update look angle
                    }
                } else {
                    playerAngles.put(player.getPlainTextName(), currentPlayerLookAngle);
                }
            }
        } catch (RuntimeException e) {
            System.out.println("AFK procedure broke");
        }
    }
}