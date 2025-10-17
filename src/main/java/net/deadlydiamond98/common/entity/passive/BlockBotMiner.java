package net.deadlydiamond98.common.entity.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.harvest.HarvestOreGoal;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockBotMiner extends BlockBotCollector {

    public BlockBotMiner(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (player.isSneaking()) {
            if (stack.getItem() instanceof BlockItem blockItem) {
                this.setOffhandStack(blockItem.getDefaultStack());
            } else {
                this.getOffHandStack().setCount(0);
            }
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new HarvestOreGoal(this, 1, 15));
    }

    @Override
    public ItemStack getMainHandStack() {
        return new ItemStack(Items.IRON_PICKAXE);
    }

    @Override
    public BehaviorState getDefaultFollowState() {
        return BehaviorState.FOLLOWING;
    }

    public @Nullable Block getTargetBlock() {
        if (this.getOffHandStack().getItem() instanceof BlockItem blockItem) {
            return blockItem.getBlock();
        }
        return null;
    }
}
