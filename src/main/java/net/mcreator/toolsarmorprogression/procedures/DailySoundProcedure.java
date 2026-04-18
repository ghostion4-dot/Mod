package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

public class DailySoundProcedure {
    static boolean playedOnce;
    static boolean playedTwice;
    static boolean playedThrice;
    public static int playSound() {
        if (Toolsarmorprogression.tickCounter % 4 == 0) {
            switch ((Toolsarmorprogression.tickCounter/ 24000) % 4) {
                case 0: //day 4
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        if (playedOnce) {
                            if (playedTwice) {
                                if (playedThrice) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.8");
                                    playedOnce = playedTwice = playedThrice = false;
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.6");
                                    playedThrice = true;
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.4");
                                playedTwice = true;
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                            playedOnce = true;
                        }
                    } break;
                case 1: //day 1
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        if (playedOnce) {
                            if (playedTwice) {
                                if (playedThrice) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                                    playedOnce = playedTwice = playedThrice = false;
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1");
                                    playedThrice = true;
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 0.8");
                                playedTwice = true;
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 0.6");
                            playedOnce = true;
                        }
                    } break;
                case 2: //day 2
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        if (playedOnce) {
                            if (playedTwice) {
                                if (playedThrice) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.4");
                                    playedOnce = playedTwice = playedThrice = false;
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                                    playedThrice = true;
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1");
                                playedTwice = true;
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 0.8");
                            playedOnce = true;
                        }
                    } break;
                default: //day 3
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        if (playedOnce) {
                            if (playedTwice) {
                                if (playedThrice) {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.6");
                                    playedOnce = playedTwice = playedThrice = false;
                                    Toolsarmorprogression.soundQueue = false;
                                } else {
                                    Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.4");
                                    playedThrice = true;
                                }
                            } else {
                                Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1.2");
                                playedTwice = true;
                            }
                        } else {
                            Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master " + player.getPlainTextName() + " " + player.getX() + " " + player.getY() + " " + player.getZ() + " 1 1");
                            playedOnce = true;
                        }
                    } break;
            }
        } return 1;
    }
}
