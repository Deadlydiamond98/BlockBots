package net.deadlydiamond98.common.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MalfunctioningBlockBotEntity extends PathAwareEntity {

    // Aesthetic Variables
    private static final TrackedData<Integer> WIRE_COLOR = DataTracker.registerData(MalfunctioningBlockBotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> WIRES = DataTracker.registerData(MalfunctioningBlockBotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> RUSTY = DataTracker.registerData(MalfunctioningBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public MalfunctioningBlockBotEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 2;
    }

    protected void initGoals() {
//        this.goalSelector.add(0, new SwimGoal(this));
//        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
//        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
//        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6));
//        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.dataTracker.set(WIRES, nbt.getInt("Wires"));
        this.dataTracker.set(WIRE_COLOR, nbt.getInt("WiresColor"));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("Wires", this.dataTracker.get(WIRES));
        nbt.putInt("WiresColor", this.dataTracker.get(WIRE_COLOR));
        return super.writeNbt(nbt);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(WIRES, 0);
        this.dataTracker.startTracking(WIRE_COLOR, 0);
        this.dataTracker.startTracking(RUSTY, false);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.dataTracker.set(WIRES, getWiresInitial());
        this.dataTracker.set(WIRE_COLOR, getRandom().nextBetween(1, 4));
//        this.dataTracker.set(RUSTY, applyRust());
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    private Integer getWiresInitial() {
        int wireNum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = this.getRandom().nextFloat() <= 0.5f ? 2 : 1;
            wireNum += (int) (digit * Math.pow(10, i));
        }
        return wireNum;
    }

//    private Boolean applyRust() {
//        this.getWorld().getBiome(this.getBlockPos()).value()
//    }

    public int getWireColor() {
        return this.dataTracker.get(WIRE_COLOR);
    }

    public boolean[] getWires() {
        int wireNum = this.dataTracker.get(WIRES);
        boolean[] bls = new boolean[10];

        for (int i = 9; i >= 0; i--) {
            int place = (int) (wireNum / Math.pow(10, i));
            bls[i] = place % 2 == 0;
        }

        return bls;
    }
}
