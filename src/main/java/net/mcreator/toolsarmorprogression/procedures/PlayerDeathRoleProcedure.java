package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import java.io.FileWriter;
import java.io.IOException;


public class PlayerDeathRoleProcedure {
    public static void playerDeaths() {
        try (FileWriter bw = new FileWriter("playerDeaths.txt")) {
            bw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) {
            try (FileWriter bw = new FileWriter("playerDeaths.txt", true)) {
                bw.write("\n" + player.getPlainTextName() + " " + player.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
