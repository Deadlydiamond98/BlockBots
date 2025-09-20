package net.deadlydiamond98.common.entity.hostile;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.items.battery.IEnergyItem;
import net.deadlydiamond98.common.misc.BlockBotsTags;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

public class FaultyBlockBotEntity extends BaseBlockBotEntity {

    // Aesthetic Variables
    private static final TrackedData<Integer> WIRE_COLOR = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> WIRES = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> RUSTY = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> TARGETING = DataTracker.registerData(FaultyBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public FaultyBlockBotEntity(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 5;
    }

    protected void initGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (!getWorld().isClient) {
            this.dataTracker.set(TARGETING, this.getTarget() != null);

            if (this.age % 30 == 0 && getWorld().getFluidState(getBlockPos()).isIn(FluidTags.WATER)) {
                this.dataTracker.set(RUSTY, true);
            }
        }
    }

    @Nullable
    @Override
    public ItemEntity dropStack(ItemStack stack, float yOffset) {
        if (stack.getItem() instanceof IEnergyItem energyItem) {
            energyItem.setBatteryLvl(stack, getRandom().nextBetween(5, 24));
        }
        return super.dropStack(stack, yOffset);
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
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("Wires", this.dataTracker.get(WIRES));
        nbt.putInt("WiresColor", this.dataTracker.get(WIRE_COLOR));
        nbt.putBoolean("Rusty", this.dataTracker.get(RUSTY));
        return super.writeNbt(nbt);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(WIRES, 0);
        this.dataTracker.startTracking(WIRE_COLOR, 0);
        this.dataTracker.startTracking(RUSTY, false);
        this.dataTracker.startTracking(TARGETING, false);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.dataTracker.set(WIRES, getWiresInitial());
        this.dataTracker.set(WIRE_COLOR, getRandom().nextBetween(1, 4));
        this.dataTracker.set(RUSTY, applyRust());
        this.setMossy(applyMoss());
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

    // Eye Changing

    public boolean isTargeting() {
        return this.dataTracker.get(TARGETING);
    }

    // Wires

    private Integer getWiresInitial() {
        int wireNum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = this.getRandom().nextFloat() <= 0.3f ? 2 : 1;
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

    // HOSTILE ENTITY COPIED


    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    @Override
    public void tickMovement() {
        this.tickHandSwing();
        this.updateDespawnCounter();
        super.tickMovement();
    }

    protected void updateDespawnCounter() {
        float f = this.getBrightnessAtEyes();
        if (f > 0.5F) {
            this.despawnCounter += 2;
        }
    }

    @Override
    protected boolean isDisallowedInPeaceful() {
        return true;
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return -world.getPhototaxisFavor(pos);
    }

    public static boolean canSpawnInDark(EntityType<? extends BaseBlockBotEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && canMobSpawn(type, world, spawnReason, pos, random);
    }

    @Override
    public boolean shouldDropXp() {
        return true;
    }

    @Override
    protected boolean shouldDropLoot() {
        return true;
    }
}
