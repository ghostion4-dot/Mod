package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.ArrayList;
import java.util.List;

public class DailySoundProcedure {
    static List<Boolean> playedOnce = new ArrayList<>();
    static List<Boolean> playedTwice = new ArrayList<>();
    static List<Boolean> playedThrice = new ArrayList<>();
    static List<String> players = new ArrayList<>();
    static ClientboundSetActionBarTextPacket firstTitle = new ClientboundSetActionBarTextPacket(Component.literal("D"));
    static ClientboundSetActionBarTextPacket secondTitle = new ClientboundSetActionBarTextPacket(Component.literal("Da"));
    static ClientboundSetActionBarTextPacket thirdTitle = new ClientboundSetActionBarTextPacket(Component.literal("Day"));
    public static ClientboundSetActionBarTextPacket lastTitle = new ClientboundSetActionBarTextPacket(Component.literal("Day " + Toolsarmorprogression.tickCounter/24000));
    public static void playSound() {
        lastTitle = new ClientboundSetActionBarTextPacket(Component.literal("Day " + Toolsarmorprogression.tickCounter/24000));
        if (Toolsarmorprogression.tickCounter % 4 == 0) {
            for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) {
                ClientboundSoundPacket chimePitch06 = new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, player.getX(), player.getY(),player.getZ(), 1.0f, 0.6f, Toolsarmorprogression.myWorld.getRandom().nextLong());
                ClientboundSoundPacket chimePitch08 = new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, player.getX(), player.getY(),player.getZ(), 1.0f, 0.8f, Toolsarmorprogression.myWorld.getRandom().nextLong());
                ClientboundSoundPacket chimePitch10 = new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, player.getX(), player.getY(),player.getZ(), 1.0f, 1.0f, Toolsarmorprogression.myWorld.getRandom().nextLong());
                ClientboundSoundPacket chimePitch12 = new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, player.getX(), player.getY(),player.getZ(), 1.0f, 1.2f, Toolsarmorprogression.myWorld.getRandom().nextLong());
                ClientboundSoundPacket chimePitch14 = new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, player.getX(), player.getY(),player.getZ(), 1.0f, 1.4f, Toolsarmorprogression.myWorld.getRandom().nextLong());
                ClientboundSoundPacket chimePitch16 = new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, player.getX(), player.getY(),player.getZ(), 1.0f, 1.6f, Toolsarmorprogression.myWorld.getRandom().nextLong());
                ClientboundSoundPacket chimePitch18 = new ClientboundSoundPacket(SoundEvents.NOTE_BLOCK_CHIME, SoundSource.MASTER, player.getX(), player.getY(),player.getZ(), 1.0f, 1.8f, Toolsarmorprogression.myWorld.getRandom().nextLong());
                if (!players.contains(player.getPlainTextName())) {
                    players.add(player.getPlainTextName());
                    playedOnce.add(Boolean.FALSE);
                    playedTwice.add(Boolean.FALSE);
                    playedThrice.add(Boolean.FALSE);
                } int x = players.indexOf(player.getPlainTextName());
                switch ((Toolsarmorprogression.tickCounter / 24000) % 4) {
                case 0: //day 4
                    checkPlayed(player, chimePitch12, chimePitch14, chimePitch16, chimePitch18, x);
                    break;
                case 1: //day 1
                    checkPlayed(player, chimePitch06, chimePitch08, chimePitch10, chimePitch12, x);

                    break;
                case 2: //day 2
                    checkPlayed(player, chimePitch08, chimePitch10, chimePitch12, chimePitch14, x);
                    break;
                default: //day 3
                    checkPlayed(player, chimePitch10, chimePitch12, chimePitch14, chimePitch16, x);
                    break;
            }
        }
        }
    }

    private static void checkPlayed(ServerPlayer player, ClientboundSoundPacket firstSound, ClientboundSoundPacket secondSound, ClientboundSoundPacket thirdSound, ClientboundSoundPacket lastSound, int x) {
        if (playedOnce.get(x)) {
            if (playedTwice.get(x)) {
                if (playedThrice.get(x)) {
                    setPlayed(x, lastTitle, lastSound, 1, player);
                } else {
                    setPlayed(x, thirdTitle, thirdSound, 3, player);
                }
            } else {
                setPlayed(x, secondTitle, secondSound, 2, player);
            }
        } else {
            setPlayed(x, firstTitle, firstSound, 1, player);
        }
    }

    public static void setPlayed(int x, ClientboundSetActionBarTextPacket textPacket, ClientboundSoundPacket soundPacket, int playAmount, ServerPlayer player) {
        if (playedThrice.get(x)) {
            playedOnce.set(x, Boolean.FALSE); playedTwice.set(x, Boolean.FALSE); playedThrice.set(x, Boolean.FALSE);
            Toolsarmorprogression.soundQueue = false;
        } else {
            switch (playAmount) {
                case 1:
                    playedOnce.set(x, Boolean.TRUE);
                    break;
                case 2:
                    playedTwice.set(x, Boolean.TRUE);
                    break;
                default:
                    playedThrice.set(x, Boolean.TRUE);
                    break;
            }
        }
        player.connection.send(soundPacket);
        player.connection.send(textPacket);
    }
}
