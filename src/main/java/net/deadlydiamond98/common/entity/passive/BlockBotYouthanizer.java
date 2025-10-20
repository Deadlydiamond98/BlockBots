package net.deadlydiamond98.common.entity.passive;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.harvest.YouthanizeGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BlockBotYouthanizer extends BlockBotCollector {
    public BlockBotYouthanizer(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new YouthanizeGoal(this, 1, false));

        this.targetSelector.add(0, new ActiveTargetGoal<>(this, AnimalEntity.class, true,
                animal -> !animal.isBaby() && !(animal instanceof BeeEntity) && !(animal instanceof FrogEntity) && this.getInventory().isEmpty())
        );
    }

    @Override
    public Identifier getEyeTexture(BaseBlockBotEntity entity) {
        if (this.ouchieTicks > 0) {
            return getEye("ouchie");
        }
        return getEye(isBlinking(entity) ? "blink" : "annoyed_left");
    }

    @Override
    public boolean showMouth(BaseBlockBotEntity entity) {
        return true;
    }

    @Override
    public Identifier getMouthTexture(BaseBlockBotEntity entity) {
        return getMouth("smuguwu");
    }
}
