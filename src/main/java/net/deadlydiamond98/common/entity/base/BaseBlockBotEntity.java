package net.deadlydiamond98.common.entity.base;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.util.IBotScreenDisplay;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class BaseBlockBotEntity extends PathAwareEntity implements IBotScreenDisplay<BaseBlockBotEntity> {

    private static final TrackedData<Boolean> MOSSY = DataTracker.registerData(BaseBlockBotEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

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
    }

    public boolean isMossy() {
        return this.dataTracker.get(MOSSY);
    }

    public void setMossy(boolean bl) {
        this.dataTracker.set(MOSSY, bl);
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
}
