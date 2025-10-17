package net.deadlydiamond98.common.entity.base;

import net.deadlydiamond98.common.misc.BlockBotsSounds;
import net.deadlydiamond98.util.IBotScreenDisplay;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class BaseBlockBotEntity extends PathAwareEntity implements IBotScreenDisplay<BaseBlockBotEntity> {

    private static final TrackedData<Boolean> MOSSY = DataTracker.registerData(BaseBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Float> MAIN_HAND_ITEM_ANGLE = DataTracker.registerData(BaseBlockBotEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Float> OFF_HAND_ITEM_ANGLE = DataTracker.registerData(BaseBlockBotEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Boolean> TARGETING = DataTracker.registerData(BaseBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected int ouchieTicks, blinkTimer;
    protected int screenAlpha = 25;

    public BaseBlockBotEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        blinkTimer += getRandom().nextFloat() <= 0.4 || isBlinking(this) ? 1 : 0;
        super.tick();
        ouchieTicks--;
        if (!getWorld().isClient) {
            this.dataTracker.set(TARGETING, this.getTarget() != null);
            if (this.isTouchingWaterOrRain() && this.age % 15 == 0) {
                this.damage(this.getDamageSources().generic(), 1);
            }
        }
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    // Screen Alpha

    @Override
    public int getScreenAlpha(BaseBlockBotEntity entity) {
        return this.screenAlpha;
    }

    @Override
    protected void updatePostDeath() {
        super.updatePostDeath();
        this.screenAlpha = Math.min(this.screenAlpha + 10, 255);
    }


    // Ouchie Eyes

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (amount > 0) {
            this.ouchieTicks = 9;
        }
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        this.ouchieTicks = 100;
    }

    @Override
    public Identifier getEyeTexture(BaseBlockBotEntity entity) {
        if (this.ouchieTicks > 0) {
            return getEye("ouchie");
        }
        return IBotScreenDisplay.super.getEyeTexture(entity);
    }

    @Override
    public int getBlinkTimer(BaseBlockBotEntity entity) {
        return entity.blinkTimer;
    }

    // Moss Carpet Hat

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(MOSSY, false);
        this.dataTracker.startTracking(MAIN_HAND_ITEM_ANGLE, 30.0f);
        this.dataTracker.startTracking(OFF_HAND_ITEM_ANGLE, 30.0f);
        this.dataTracker.startTracking(TARGETING, false);
    }

    public boolean isTargeting() {
        return this.dataTracker.get(TARGETING);
    }

    public boolean isMossy() {
        return this.dataTracker.get(MOSSY);
    }

    public void setMossy(boolean bl) {
        this.dataTracker.set(MOSSY, bl);
    }

    public float getMainHeldItemAngle() {
        return this.dataTracker.get(MAIN_HAND_ITEM_ANGLE);
    }

    public void setMainHeldItemAngle(float angle) {
        this.dataTracker.set(MAIN_HAND_ITEM_ANGLE, angle);
    }

    public float getOffHeldItemAngle() {
        return this.dataTracker.get(OFF_HAND_ITEM_ANGLE);
    }

    public void setOffHeldItemAngle(float angle) {
        this.dataTracker.set(OFF_HAND_ITEM_ANGLE, angle);
    }

    public void setMainhandStack(ItemStack stack) {
        equipStack(EquipmentSlot.MAINHAND, stack);
    }

    public void setOffhandStack(ItemStack stack) {
        equipStack(EquipmentSlot.OFFHAND, stack);
    }

    @Override
    protected void drop(DamageSource source) {
        super.drop(source);
        if (this.isMossy() && this.getRandom().nextFloat() <= 0.4) {
            dropItem(Blocks.MOSS_CARPET.asItem());
        }
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!this.getWorld().isClient) {
            if (itemStack.isOf(Items.SHEARS)) {
                if (this.isMossy()) {
                    this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getBlockX(), this.getBlockY(), this.getBlockZ(),  new ItemStack(Blocks.MOSS_CARPET)));
                    setMossy(false);
                    this.emitGameEvent(GameEvent.SHEAR, player);
                    itemStack.damage(1, player, (playerx) -> playerx.sendToolBreakStatus(hand));
                    return ActionResult.SUCCESS;
                }
                return ActionResult.CONSUME;
            } else if (itemStack.isOf(Blocks.MOSS_CARPET.asItem())) {
                if (!this.isMossy()) {
                    this.getWorld().playSoundFromEntity(null, this, SoundEvents.BLOCK_MOSS_CARPET_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    setMossy(true);
                    itemStack.decrement(1);
                    return ActionResult.SUCCESS;
                }
                return ActionResult.CONSUME;
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        setMossy(nbt.getBoolean("Mossy"));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("Mossy", isMossy());
        return super.writeNbt(nbt);
    }

    public static DefaultAttributeContainer.Builder createCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return BlockBotsSounds.BLOCK_BOT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return BlockBotsSounds.BLOCK_BOT_DEATH;
    }
}
