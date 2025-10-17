package net.deadlydiamond98.common.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.world.World;

public abstract class SpinningThrownItemEntity extends ThrownItemEntity {

    private static final TrackedData<Integer> ROT_OFFSET = DataTracker.registerData(SpinningThrownItemEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> SPIN_SPEED = DataTracker.registerData(SpinningThrownItemEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> SPIN_DIRECTION = DataTracker.registerData(SpinningThrownItemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public SpinningThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public SpinningThrownItemEntity(EntityType<? extends ThrownItemEntity> entityType, LivingEntity livingEntity, World world) {
        super(entityType, livingEntity, world);
        this.dataTracker.set(ROT_OFFSET, world.getRandom().nextBetween(0, 360));
        this.dataTracker.set(SPIN_SPEED, world.getRandom().nextBetween(3, 6));
        this.dataTracker.set(SPIN_DIRECTION, world.getRandom().nextBoolean());
    }

    public int getRotOffset() {
        return this.dataTracker.get(ROT_OFFSET);
    }

    public int getSpinMultOffset() {
        return this.dataTracker.get(SPIN_SPEED);
    }

    public boolean getSpinDirection() {
        return this.dataTracker.get(SPIN_DIRECTION);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ROT_OFFSET, 0);
        this.dataTracker.startTracking(SPIN_SPEED, 5);
        this.dataTracker.startTracking(SPIN_DIRECTION, true);
    }
}
