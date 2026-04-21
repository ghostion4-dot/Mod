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
    static List<ServerPlayer> players = new ArrayList<>();
    public static void playerDeaths() {
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
        for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers() ) {
            players.add(player);
            playerIsIn.add(Boolean.FALSE);
            for (String line : text) {
                if (!line.isBlank()) {
                    if (line.contains(player.getPlainTextName())) {
                        playerIsIn.set(players.indexOf(player), Boolean.TRUE);
                        if (!line.endsWith(" " + player.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS)))) {
                            line = player.getPlainTextName() + " " + player.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS));
                            try (FileWriter myWriter = new FileWriter("playerDeaths.txt", true)) {
                                myWriter.write("\n" + line);
                                break;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }


        }for (Boolean isIn : playerIsIn) {
            if (!isIn) {
                ServerPlayer player = players.get(playerIsIn.indexOf(Boolean.FALSE));
                try (FileWriter bw = new FileWriter("playerDeaths.txt", true)) {
                    bw.write("\n" + player.getPlainTextName() + " " + player.getStats().getValue(Stats.CUSTOM.get(Stats.DEATHS)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
