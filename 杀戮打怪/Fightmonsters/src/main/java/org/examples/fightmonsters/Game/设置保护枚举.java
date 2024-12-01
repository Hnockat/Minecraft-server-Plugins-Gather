package org.examples.fightmonsters.Game;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public class 设置保护枚举 {
    public enum Protect_enumerations {
        PLAYER,
        ALLAY,
        AXOLOTL,
        CAT,
        SHULKER,
        SNOW_GOLEM,
        VILLAGER,
        WANDERING_TRADER,
        WOLF,

        ARROW,
        DRAGON_FIREBALL,
        EGG,
        ENDER_PEARL,
        EXPERIENCE_BOTTLE,
        FIREBALL,
        FIREWORK_ROCKET,
        FISHING_BOBBER,
        LLAMA_SPIT,
        POTION,
        SHULKER_BULLET,
        SMALL_FIREBALL,
        SNOWBALL,
        SPECTRAL_ARROW,
        TRIDENT,  // 注意：确保这是正确的枚举名称，但在Minecraft中，三叉戟作为投掷物时通常不视为单独的实体类型
        WITHER_SKULL,

        BOAT,
        CHEST_BOAT,
        CHEST_MINECART,
        COMMAND_BLOCK_MINECART,
        FURNACE_MINECART,
        HOPPER_MINECART,
        MINECART,
        SPAWNER_MINECART,
        TNT_MINECART,

        ITEM,
        AREA_EFFECT_CLOUD,
        ARMOR_STAND,
        END_CRYSTAL,
        EXPERIENCE_ORB,
        EYE_OF_ENDER,
        FALLING_BLOCK,
        GLOW_ITEM_FRAME,
        ITEM_FRAME,
        LEASH_KNOT,
        LIGHTNING_BOLT,
        PAINTING,
        TNT
    }
    private static final Map<String, Protect_enumerations> nameMap = new HashMap<>();
    static {
        for (Protect_enumerations type : Protect_enumerations.values()) {
            nameMap.put(type.name(), type);
        }
    }
    public static boolean isProtectedEntityType(String name) {
        return nameMap.containsKey(name);
    }
}
