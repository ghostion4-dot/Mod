package net.mcreator.toolsarmorprogression.procedures;

import net.mcreator.toolsarmorprogression.Toolsarmorprogression;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Objects;


public class AttributeRandomizerProcedure {
    public static boolean randomAttributes;
    public static void attributeRandomizer() {
        if (randomAttributes) {
            for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) {
                Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.ARMOR_TOUGHNESS)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 1);
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 5);
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 10);
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_KNOCKBACK)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.BLOCK_BREAK_SPEED)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 10);
                Objects.requireNonNull(player.getAttribute(Attributes.BURNING_TIME)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.CAMERA_DISTANCE)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 10);
                Objects.requireNonNull(Objects.requireNonNull(player.getAttribute(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE))).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 10);
                Objects.requireNonNull(player.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 5);
                Objects.requireNonNull(player.getAttribute(Attributes.GRAVITY)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() / 2);
                Objects.requireNonNull(Objects.requireNonNull(player.getAttribute(Attributes.JUMP_STRENGTH))).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 5);
                Objects.requireNonNull(player.getAttribute(Attributes.KNOCKBACK_RESISTANCE)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.LUCK)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.MAX_ABSORPTION)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 50);
                Objects.requireNonNull(player.getAttribute(Attributes.MINING_EFFICIENCY)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_EFFICIENCY)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 1);
                Objects.requireNonNull(player.getAttribute(Attributes.OXYGEN_BONUS)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.SAFE_FALL_DISTANCE)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(Objects.requireNonNull(player.getAttribute(Attributes.SCALE))).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 2);
                Objects.requireNonNull(player.getAttribute(Attributes.SNEAKING_SPEED)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(player.getAttribute(Attributes.STEP_HEIGHT)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 5);
                Objects.requireNonNull(player.getAttribute(Attributes.SUBMERGED_MINING_SPEED)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);
                Objects.requireNonNull(Objects.requireNonNull(player.getAttribute(Attributes.SWEEPING_DAMAGE_RATIO))).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 1);
                Objects.requireNonNull(player.getAttribute(Attributes.WATER_MOVEMENT_EFFICIENCY)).setBaseValue(Toolsarmorprogression.myWorld.getRandom().nextDouble() * 20);

            }
        }
    } public static void resetAttributes() {
        for (ServerPlayer player : Toolsarmorprogression.myServer.getPlayerList().getPlayers()) {
            LivingEntity entity = player.getLivingEntity();
            entity.getAttributes().resetBaseValue(Attributes.ARMOR_TOUGHNESS);
            entity.getAttributes().resetBaseValue(Attributes.ATTACK_DAMAGE);
            entity.getAttributes().resetBaseValue(Attributes.ATTACK_SPEED);
            entity.getAttributes().resetBaseValue(Attributes.ATTACK_KNOCKBACK);
            entity.getAttributes().resetBaseValue(Attributes.BLOCK_BREAK_SPEED);
            entity.getAttributes().resetBaseValue(Attributes.BLOCK_INTERACTION_RANGE);
            entity.getAttributes().resetBaseValue(Attributes.BURNING_TIME);
            entity.getAttributes().resetBaseValue(Attributes.CAMERA_DISTANCE);
            entity.getAttributes().resetBaseValue(Attributes.ENTITY_INTERACTION_RANGE);
            entity.getAttributes().resetBaseValue(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE);
            entity.getAttributes().resetBaseValue(Attributes.FALL_DAMAGE_MULTIPLIER);
            entity.getAttributes().resetBaseValue(Attributes.GRAVITY);
            entity.getAttributes().resetBaseValue(Attributes.JUMP_STRENGTH);
            entity.getAttributes().resetBaseValue(Attributes.KNOCKBACK_RESISTANCE);
            entity.getAttributes().resetBaseValue(Attributes.ARMOR);
            entity.getAttributes().resetBaseValue(Attributes.LUCK);
            entity.getAttributes().resetBaseValue(Attributes.MAX_ABSORPTION);
            entity.getAttributes().resetBaseValue(Attributes.MAX_HEALTH);
            entity.getAttributes().resetBaseValue(Attributes.MINING_EFFICIENCY);
            entity.getAttributes().resetBaseValue(Attributes.MOVEMENT_EFFICIENCY);
            entity.getAttributes().resetBaseValue(Attributes.MOVEMENT_SPEED);
            entity.getAttributes().resetBaseValue(Attributes.OXYGEN_BONUS);
            entity.getAttributes().resetBaseValue(Attributes.SAFE_FALL_DISTANCE);
            entity.getAttributes().resetBaseValue(Attributes.SCALE);
            entity.getAttributes().resetBaseValue(Attributes.SNEAKING_SPEED);
            entity.getAttributes().resetBaseValue(Attributes.STEP_HEIGHT);
            entity.getAttributes().resetBaseValue(Attributes.SUBMERGED_MINING_SPEED);
            entity.getAttributes().resetBaseValue(Attributes.SWEEPING_DAMAGE_RATIO);
            entity.getAttributes().resetBaseValue(Attributes.WATER_MOVEMENT_EFFICIENCY);

        }
    }
}
