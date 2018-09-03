package com.teamsekc.whyisnteverythingexploding.mixins;

import com.teamsekc.whyisnteverythingexploding.enums.ExplosionValues;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingEvents extends Entity {

    public MixinEntityLivingEvents(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    @Inject(method = "onDeath", at = @At("RETURN"))
    public void onDeath(DamageSource damageSource, CallbackInfo ci) {
        this.explodeEntity(ExplosionValues.DEATH);
    }

    @Inject(method = "jump", at = @At("RETURN"))
    protected void jump(CallbackInfo ci) {
        this.explodeEntity(ExplosionValues.JUMP);
    }

    @Inject(method = "damageEntity", at = @At("RETURN"))
    protected void damageEntity(DamageSource p_damageEntity_1_, float p_damageEntity_2_, CallbackInfo ci) {
        this.explodeEntity(ExplosionValues.DAMAGED);
    }

    @Inject(method = "onUpdate", at = @At("INVOKE"))
    public void onUpdate(CallbackInfo ci) {
        if(this.isSprinting()) {
            this.explodeEntity(ExplosionValues.SPRINTING);
        }
    }

    private void explodeEntity(ExplosionValues explosion) {
        if (!this.world.isRemote && this.shouldExplode(explosion)) {
            float average = (this.width + this.height) * 0.5f * explosion.getSize();
            boolean mobGriefing = explosion.getDamageWorld() && this.world.getGameRules().getBoolean("mobGriefing");
            this.world.createExplosion(explosion.getCanDamageSelf() ? null : this, this.posX, this.posY, this.posZ, average, mobGriefing);
        }
    }

    private boolean shouldExplode(ExplosionValues value) {
        return Math.random() < value.getChance();
    }

}
