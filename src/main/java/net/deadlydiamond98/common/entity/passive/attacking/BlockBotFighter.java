package net.deadlydiamond98.common.entity.passive.attacking;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.attack.BotMeleeAttackGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BlockBotFighter extends BlockBotAttacker {

    public BlockBotFighter(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new BotMeleeAttackGoal(this, 1, false));
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, MobEntity.class, 10, true, true,
                (entity) -> entity instanceof Monster && !(entity instanceof CreeperEntity))
        );
    }

    @Override
    public ItemStack getMainHandStack() {
        return new ItemStack(Items.IRON_SWORD);
    }

    public static DefaultAttributeContainer.Builder createCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }
}
