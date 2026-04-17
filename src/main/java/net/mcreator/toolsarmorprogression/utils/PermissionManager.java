package net.mcreator.toolsarmorprogression.utils;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

import static net.mcreator.toolsarmorprogression.Toolsarmorprogression.myServer;


public class PermissionManager {
    public static boolean isAdmin(ServerPlayer player) {
        if (player == null) return false;
        if (myServer == null) return false;
        //? if < 1.21.9 {
        /*return server.getPlayerList().isOp(player.getGameProfile());
         *///?} else {
        return myServer.getPlayerList().isOp(player.nameAndId());
        //?}
    }

    public static boolean isAdmin(CommandSourceStack source) {
        if (source.getEntity() == null) return true;
        return isAdmin(source.getPlayer());
    }
}
