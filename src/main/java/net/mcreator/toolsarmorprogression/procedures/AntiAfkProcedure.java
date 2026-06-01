package net.mcreator.toolsarmorprogression.procedures;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AntiAfkProcedure {
     static Map<String, Vec3> playerAngles = new HashMap<>();

    public static void DetectAfk() {
        try {
            for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) { // Store every old player head rotation vector
                Vec3 currentPlayerLookAngle = player.getLookAngle();
                if (playerAngles.containsKey(player.getPlainTextName())) {
                    if (playerAngles.get(player.getPlainTextName()) == currentPlayerLookAngle) { // If player in array, kick
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