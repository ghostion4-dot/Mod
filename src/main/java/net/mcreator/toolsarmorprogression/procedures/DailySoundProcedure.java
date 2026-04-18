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
                case 0:
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master @a " + player.getX() + " " + player.getY() + " " + player.getZ() + "1 0.8");
                        Toolsarmorprogression.soundQueue = false;
                        playedOnce = playedTwice = playedThrice = false;
                    } break;
                case 1:
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master @a " + player.getX() + " " + player.getY() + " " + player.getZ() + "1 0.8");
                        Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master @a " + player.getX() + " " + player.getY() + " " + player.getZ() + "1 1");
                        System.out.println("playing everywhere");
                        Toolsarmorprogression.soundQueue = false;
                    } break;
                case 2:
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master @a " + player.getX() + " " + player.getY() + " " + player.getZ() + "1 0.8");
                        Toolsarmorprogression.myServer.getCommands().performPrefixedCommand(Toolsarmorprogression.myServer.createCommandSourceStack(), "playsound minecraft:block.note_block.chime master @a " + player.getX() + " " + player.getY() + " " + player.getZ() + "1 0.8");
                        player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 1.0f, 1.2f);
                        System.out.println("playing there");
                        Toolsarmorprogression.soundQueue = false;
                    } break;
                default:
                    for (Player player : Toolsarmorprogression.myWorld.players()) {
                        player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 1.0f, 0.8f);
                        player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 1.0f, 1.0f);
                        player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 1.0f, 1.2f);
                        player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 1.0f, 1.4f);
                        System.out.println("playing here");
                        Toolsarmorprogression.soundQueue = false;
                    } break;
            }
        } return 1;
    }
}
