package com.teamsekc.whyisnteverythingexploding.mixins;

import com.teamsekc.whyisnteverythingexploding.enums.ExplosionValues;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
@Mixin(EntityPlayer.class)
public abstract class MixinPlayerEvents extends EntityLivingBase {
    protected MixinPlayerEvents(EntityType<?> p_i48577_1_, World p_i48577_2_) {
        super(p_i48577_1_, p_i48577_2_);
    }

    @Inject(method = "trySleep", at = @At("RETURN"))
    public EntityPlayer.SleepResult trySleep(BlockPos p_trySleep_1_, CallbackInfoReturnable ci) {
            this.explodeEntity(ExplosionValues.SLEEP);
            return (EntityPlayer.SleepResult) ci.getReturnValue();
    }

    private void explodeEntity(ExplosionValues explosion) {
        if (!this.world.isRemote && ExplosionValues.shouldExplode(explosion)) {
            float average = (this.width + this.height) * 0.5f * explosion.size;
            boolean mobGriefing = explosion.damageWorld && this.world.getGameRules().getBoolean("mobGriefing");
            this.world.createExplosion(explosion.canDamageSelf ? null : this, this.posX, this.posY, this.posZ, average, mobGriefing);
        }
    }

}
