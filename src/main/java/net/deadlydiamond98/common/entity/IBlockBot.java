package net.deadlydiamond98.common.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.event.GameEvent;

public interface IBlockBot<T extends PathAwareEntity> {
    boolean isMossy();
    void setMossy(boolean bl);

    void setHurtTime(int i);
    int getHurtTime();

    default ActionResult useItemOnMob(PlayerEntity player, Hand hand, T entity) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!entity.getWorld().isClient) {
            if (itemStack.isOf(Items.SHEARS)) {
                if (this.isMossy()) {
                    entity.getWorld().playSoundFromEntity(null, entity, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entity.getWorld().spawnEntity(new ItemEntity(entity.getWorld(), entity.getBlockX(), entity.getBlockY(), entity.getBlockZ(),  new ItemStack(Blocks.MOSS_CARPET)));
                    setMossy(false);
                    entity.emitGameEvent(GameEvent.SHEAR, player);
                    itemStack.damage(1, player, (playerx) -> playerx.sendToolBreakStatus(hand));
                    return ActionResult.SUCCESS;
                }
                return ActionResult.CONSUME;
            } else if (itemStack.isOf(Blocks.MOSS_CARPET.asItem())) {
                if (!this.isMossy()) {
                    entity.getWorld().playSoundFromEntity(null, entity, SoundEvents.BLOCK_MOSS_CARPET_PLACE, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    setMossy(true);
                    itemStack.decrement(1);
                    return ActionResult.SUCCESS;
                }
                return ActionResult.CONSUME;
            }
        }
        return ActionResult.PASS;
    }
}
