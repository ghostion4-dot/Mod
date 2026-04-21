package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PlayerDeathRoleProcedure {
    static List<String> text = new ArrayList<>();
    static List<Boolean> playerIsIn = new ArrayList<>();
    static List<String> players = new ArrayList<>();
    public static void playerDeaths() {
        boolean doContinue = false;
        text.clear();
        playerIsIn.clear();
        players.clear();
        try {
            File file = new File("playerDeaths.txt");
            if (!file.createNewFile() && file.length() > 0) {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    text.add(reader.nextLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : text ) {
            for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) {
                for (String x : players) if (player.getPlainTextName().equals(x) && playerIsIn.get(players.indexOf(player.getPlainTextName()))) {doContinue = true;}
                if (doContinue) continue;
                players.add(player.getPlainTextName());
                playerIsIn.add(false);
                if (line.contains(player.getPlainTextName())) {
                    playerIsIn.set(players.indexOf(player.getPlainTextName()), true);
                    if (!line.endsWith(" " + player.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS)))) {
                        line = player.getPlainTextName() + " " + player.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS));
                    }
                }
            } try (BufferedWriter bw = new BufferedWriter(new FileWriter("playerDeaths.txt"))) {
                bw.write(line);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
