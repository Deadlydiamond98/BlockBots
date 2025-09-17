package net.deadlydiamond98.common.entity;

import net.deadlydiamond98.common.misc.BlockBotsTags;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FaultyBlockBotEntity extends HostileEntity implements IBlockBot<FaultyBlockBotEntity> {

    // Aesthetic Variables
    private static final TrackedData<Integer> WIRE_COLOR = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> WIRES = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private static final TrackedData<Boolean> RUSTY = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> MOSSY = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private static final TrackedData<Integer> OUCHIE = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> TARGETING = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public FaultyBlockBotEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if (!getWorld().isClient) {
            int hurtTime = getHurtTime();
            this.dataTracker.set(OUCHIE, Math.max(0, --hurtTime));
            this.dataTracker.set(TARGETING, this.getTarget() != null);

            if (this.age % 30 == 0 && getWorld().getFluidState(getBlockPos()).isIn(FluidTags.WATER)) {
                this.dataTracker.set(RUSTY, true);
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!this.getWorld().isClient() && amount > 0) {
            this.dataTracker.set(OUCHIE, 9);
        }
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        this.dataTracker.set(OUCHIE, 100);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        return this.useItemOnMob(player, hand, this);
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
        this.dataTracker.set(RUSTY, nbt.getBoolean("Rusty"));
        this.dataTracker.set(MOSSY, nbt.getBoolean("Mossy"));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("Wires", this.dataTracker.get(WIRES));
        nbt.putInt("WiresColor", this.dataTracker.get(WIRE_COLOR));
        nbt.putBoolean("Rusty", this.dataTracker.get(RUSTY));
        nbt.putBoolean("Mossy", this.dataTracker.get(MOSSY));
        return super.writeNbt(nbt);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(WIRES, 0);
        this.dataTracker.startTracking(WIRE_COLOR, 0);
        this.dataTracker.startTracking(OUCHIE, 0);
        this.dataTracker.startTracking(RUSTY, false);
        this.dataTracker.startTracking(MOSSY, false);
        this.dataTracker.startTracking(TARGETING, false);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.dataTracker.set(WIRES, getWiresInitial());
        this.dataTracker.set(WIRE_COLOR, getRandom().nextBetween(1, 4));
        this.dataTracker.set(RUSTY, applyRust());
        this.dataTracker.set(MOSSY, applyMoss());
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    // Rust

    private boolean applyRust() {
        boolean bl = this.getWorld().getBiome(getBlockPos()).isIn(BlockBotsTags.Biomes.RUSTY);
        boolean bl2 = getWorld().getFluidState(getBlockPos()).isIn(FluidTags.WATER);
        return bl && random.nextFloat() <= 0.4 || bl2;
    }

    public boolean isRusty() {
        return this.dataTracker.get(RUSTY);
    }

    // Moss

    private boolean applyMoss() {
        boolean bl = this.getWorld().getBiome(getBlockPos()).isIn(BlockBotsTags.Biomes.MOSSY);
        boolean bl2 = getWorld().getBlockState(getBlockPos().down()).isIn(BlockBotsTags.Blocks.MOSS_SPAWN);
        return bl && random.nextFloat() <= 0.4 || bl2;
    }

    @Override
    public boolean isMossy() {
        return this.dataTracker.get(MOSSY);
    }

    @Override
    public void setMossy(boolean bl) {
        this.dataTracker.set(MOSSY, bl);
    }

    @Override
    protected void drop(DamageSource source) {
        super.drop(source);
        if (this.isMossy()) {
            dropItem(Blocks.MOSS_CARPET.asItem());
        }
    }

    // Eye Changing

    public int getHurtTime() {
        return this.dataTracker.get(OUCHIE);
    }

    @Override
    public void setHurtTime(int i) {
        this.dataTracker.set(OUCHIE, i);
    }

    public boolean isTargeting() {
        return this.dataTracker.get(TARGETING);
    }

    // Wires

    private Integer getWiresInitial() {
        int wireNum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = this.getRandom().nextFloat() <= 0.5f ? 2 : 1;
            wireNum += (int) (digit * Math.pow(10, i));
        }
        return wireNum;
    }

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
