package net.deadlydiamond98.common.entity.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.ownergoal.BlockBotFollowOwnerGoal;
import net.deadlydiamond98.common.entity.passive.base.OwnedBlockBotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class BlockBotHealer extends OwnedBlockBotEntity {

    private static final TrackedData<Boolean> IS_HEALING = DataTracker.registerData(BlockBotHealer.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int cantFindTicks = 0;

    public BlockBotHealer(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (!getWorld().isClient) {
            if (isCloseToOwner()) {
                this.getOwner().addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 30, 0, false, false));
            }

            getWorld().getEntitiesByClass(LivingEntity.class, getBoundingBox().expand(5), entity -> !(entity instanceof Monster))
                    .forEach(living -> living.addStatusEffect(
                            new StatusEffectInstance(StatusEffects.REGENERATION, 30, 0, false, false)
                    ));

            this.dataTracker.set(IS_HEALING, isCloseToOwner());
        }

        if (isCloseToOwner() && this.age % 20 == 0) {
            getWorld().addParticle(ParticleTypes.HEART, this.getX(), this.getY() + 0.75, this.getZ(), 0, 0, 0);
        }
    }

    private boolean isCloseToOwner() {
        return this.getOwner() != null && this.getOwner().distanceTo(this) < 8;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(IS_HEALING, false);
    }

    @Override
    public boolean canGather(ItemStack stack) {
        return false;
    }

    @Override
    public Identifier getEyeTexture(BaseBlockBotEntity entity) {
        if (this.ouchieTicks > 0) {
            return getEye("ouchie");
        } else if (this.dataTracker.get(IS_HEALING)) {
            this.cantFindTicks = 0;
            return getEye("happi");
        } else if (this.cantFindTicks++ >= 100) {
            return getEye("sad");
        }

        return super.getEyeTexture(entity);
    }

    @Override
    public boolean showMouth(BaseBlockBotEntity entity) {
        return this.cantFindTicks >= 100;
    }

    @Override
    public Identifier getMouthTexture(BaseBlockBotEntity entity) {
        return getMouth("sad");
    }

    @Override
    public BehaviorState getDefaultFollowState() {
        return BehaviorState.FOLLOWING;
    }
}
