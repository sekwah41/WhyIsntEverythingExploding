package com.teamsekc.whyisnteverythingexploding.enums;

public enum ExplosionValues {

    DEATH(0.4f, 1.1f, true, true),
    DAMAGED(0.05f, 0.7f, false, true),
    SPRINTING(0.0007f, 0.3f, false, false),
    SLEEP(0.01f, 4f, false, true),
    JUMP(0.04f, 0.5f, false, false);

    public final float chance;

    public final float size;

    public final boolean damageWorld;

    public final boolean canDamageSelf;

    ExplosionValues(float chance, float size, boolean damageWorld, boolean canDamageSelf) {
        this.chance = chance;
        this.size = size;
        this.damageWorld = damageWorld;
        this.canDamageSelf = canDamageSelf;
    }

    public static boolean shouldExplode(ExplosionValues value) {
        return Math.random() < value.chance;
    }
}
