package net.mcreator.toolsarmorprogression.procedures;
import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import java.util.ArrayList;
import java.util.List;


public class AntiAfkProcedure {
    static List<String> PlayersNames = new ArrayList<>();
    static List<Vec3> oldAngles = new ArrayList<>();
    public static void DetectAfk() {
        try {
            for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) { // Store every old player head rotation vector
                Vec3 currentPlayerLookAngle = player.getLookAngle();
                if (PlayersNames.contains(player.getName().getString())) {
                    int x = PlayersNames.indexOf(player.getPlainTextName());
                    if (oldAngles.indexOf(currentPlayerLookAngle) == x) { // If player in array, kick
                        System.out.println(player.getPlainTextName() + " is afk. Kicking.");
                        player.connection.disconnect(Component.literal("fatass"));
                    } else { // or don't
                        oldAngles.set(x, currentPlayerLookAngle); // If player has moved, update look angle
                    }
                } else {
                    PlayersNames.add(player.getPlainTextName());
                    oldAngles.add(currentPlayerLookAngle);
                }
            }
        } catch (RuntimeException e) {
            System.out.println("AFK procedure broke");
        }
    }
}