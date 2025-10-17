package net.deadlydiamond98.common.entity.passive.attacking;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.passive.base.OwnedBlockBotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BlockBotAttacker extends OwnedBlockBotEntity {
    public BlockBotAttacker(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canGather(ItemStack stack) {
        return false;
    }

    @Override
    public Identifier getEyeTexture(BaseBlockBotEntity entity) {
        if (isTargeting() && this.ouchieTicks <= 0) {
            return getEye(isBlinking(entity) ? "blink" : "mean");
        }
        return super.getEyeTexture(entity);
    }

    @Override
    public BehaviorState getDefaultFollowState() {
        return BehaviorState.FOLLOWING;
    }
}
