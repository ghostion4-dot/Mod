package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class DailySoundProcedure {
    static List<Boolean> playedOnce = new ArrayList<>();
    static List<Boolean> playedTwice = new ArrayList<>();
    static List<Boolean> playedThrice = new ArrayList<>();
    static List<String> players = new ArrayList<>();
    public static int playSound() {
        if (Toolsarmorprogression.tickCounter % 4 == 0) {
            for (Player player : Toolsarmorprogression.myWorld.players()) {
                if (!players.contains(player.getPlainTextName())) {
                    players.add(player.getPlainTextName());
                    playedOnce.add(Boolean.FALSE);
                    playedTwice.add(Boolean.FALSE);
                    playedThrice.add(Boolean.FALSE);
                } int x = players.indexOf(player.getPlainTextName());
                switch ((Toolsarmorprogression.tickCounter / 24000) % 4) {
                    case 0: //day 4
                        if (playedOnce.get(x)) {
                            if (playedTwice.get(x)) {
                                if (playedThrice.get(x)) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.8");
                                    playedOnce.set(x, Boolean.FALSE); playedTwice.set(x, Boolean.FALSE); playedThrice.set(x, Boolean.FALSE);
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.6");
                                    playedThrice.set(x, Boolean.TRUE);
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.4");
                                playedTwice.set(x, Boolean.TRUE);
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                            playedOnce.set(x, Boolean.TRUE);
                        }
                break;
                case 1: //day 1
                        if (playedOnce.get(x)) {
                            if (playedTwice.get(x)) {
                                if (playedThrice.get(x)) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                                    playedOnce.set(x, Boolean.FALSE); playedTwice.set(x, Boolean.FALSE); playedThrice.set(x, Boolean.FALSE);
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1");
                                    playedThrice.set(x, Boolean.TRUE);
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 0.8");
                                playedTwice.set(x, Boolean.TRUE);
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 0.6");
                            playedOnce.set(x, Boolean.TRUE);
                        }

                    break;
                case 2: //day 2
                        if (playedOnce.get(x)) {
                            if (playedTwice.get(x)) {
                                if (playedThrice.get(x)) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.4");
                                    playedOnce.set(x, Boolean.FALSE); playedTwice.set(x, Boolean.FALSE); playedThrice.set(x, Boolean.FALSE);
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                                    playedThrice.set(x, Boolean.TRUE);
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1");
                                playedTwice.set(x, Boolean.TRUE);
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 0.8");
                            playedOnce.set(x, Boolean.TRUE);
                        }
                    break;
                default: //day 3
                        if (playedOnce.get(x)) {
                            if (playedTwice.get(x)) {
                                if (playedThrice.get(x)) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.6");
                                    playedOnce.set(x, Boolean.FALSE); playedTwice.set(x, Boolean.FALSE); playedThrice.set(x, Boolean.FALSE);
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.4");
                                    playedThrice.set(x, Boolean.TRUE);
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                                playedTwice.set(x, Boolean.TRUE);
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1");
                            playedOnce.set(x, Boolean.TRUE);
                        }
                    break;
            }
        }
        } return 1;
    }
}
