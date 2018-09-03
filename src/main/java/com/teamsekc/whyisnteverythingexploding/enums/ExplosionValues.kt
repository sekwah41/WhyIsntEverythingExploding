package com.teamsekc.whyisnteverythingexploding.enums

enum class ExplosionValues private constructor(val chance: Float, val size: Float, val damageWorld: Boolean, val canDamageSelf: Boolean) {

    DEATH(0.4f, 1.1f, true, true),
    DAMAGED(0.05f, 0.7f, false, true),
    SPRINTING(0.0007f, 0.3f, false, false),
    SLEEP(0.01f, 4f, false, true),
    JUMP(0.04f, 0.5f, false, false);

}
