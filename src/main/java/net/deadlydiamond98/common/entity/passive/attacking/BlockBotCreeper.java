package net.deadlydiamond98.common.entity.passive.attacking;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.attack.BotMeleeAttackGoal;
import net.deadlydiamond98.common.entity.goals.attack.SelfDestructGoal;
import net.deadlydiamond98.common.entity.passive.base.OwnedBlockBotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class BlockBotCreeper extends OwnedBlockBotEntity {

    private static final TrackedData<Integer> FUSE_SPEED = DataTracker.registerData(BlockBotCreeper.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int EXPLOSION_RADIUS = 3;
    private int lastFuseTime;
    private int currentFuseTime;
    private final int fuseTime = 30;

    public BlockBotCreeper(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new SelfDestructGoal(this));
        this.goalSelector.add(4, new BotMeleeAttackGoal(this, 1.0, false));

        this.targetSelector.add(0, new ActiveTargetGoal<>(this, MobEntity.class, true, entity -> entity instanceof Monster));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE_SPEED, -1);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive()) {
            this.lastFuseTime = this.currentFuseTime;

            int i = this.getFuseSpeed();
            if (i > 0 && this.currentFuseTime == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
                this.emitGameEvent(GameEvent.PRIME_FUSE);
            }

            this.currentFuseTime += i;
            if (this.currentFuseTime < 0) {
                this.currentFuseTime = 0;
            }

            if (this.currentFuseTime >= this.fuseTime) {
                this.currentFuseTime = this.fuseTime;
                this.explode();
            }
        }
    }

    private void explode() {
        if (!this.getWorld().isClient) {
            this.dead = true;
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float) EXPLOSION_RADIUS, World.ExplosionSourceType.TNT);
            this.discard();
        }
    }

    public float getClientFuseTime(float timeDelta) {
        return MathHelper.lerp(timeDelta, (float)this.lastFuseTime, (float)this.currentFuseTime) / (float)(this.fuseTime - 2);
    }

    public int getFuseSpeed() {
        return this.dataTracker.get(FUSE_SPEED);
    }

    public void setFuseSpeed(int fuseSpeed) {
        this.dataTracker.set(FUSE_SPEED, fuseSpeed);
    }


    @Override
    public Identifier getEyeTexture(BaseBlockBotEntity entity) {
        return getEye("creeper");
    }

    public static DefaultAttributeContainer.Builder createCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }

    @Override
    public boolean canGather(ItemStack stack) {
        return false;
    }
}
