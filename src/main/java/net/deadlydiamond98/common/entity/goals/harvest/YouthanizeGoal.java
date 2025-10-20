package net.deadlydiamond98.common.entity.goals.harvest;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class YouthanizeGoal extends MeleeAttackGoal {
    public YouthanizeGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
    }

    @Override
    public boolean canStart() {
        return super.canStart();
    }

    @Override
    protected void attack(LivingEntity target, double squaredDistance) {
        if (target instanceof AnimalEntity animal && !animal.isBaby()) {
            double d = this.getSquaredMaxAttackDistance(target);
            if (squaredDistance <= d && this.getCooldown() <= 0) {
                this.resetCooldown();
                this.mob.swingHand(Hand.MAIN_HAND);
                this.mob.tryAttack(target);
                this.mob.getWorld().playSound(null, this.mob.getBlockPos(), SoundEvents.ENTITY_MOOSHROOM_CONVERT, SoundCategory.PLAYERS);
                dropAnimalLoot(target);
                animal.setBaby(true);
                this.mob.setTarget(null);
                this.stop();
            }
        } else {
            this.mob.setTarget(null);
            this.stop();
        }
    }

    private void dropAnimalLoot(LivingEntity target) {
        if (!target.getWorld().isClient) {
            DamageSource damageSource = target.getDamageSources().mobAttack(this.mob);
            Identifier identifier = target.getLootTable();
            LootTable lootTable = target.getWorld().getServer().getLootManager().getLootTable(identifier);
            LootContextParameterSet.Builder builder = (new LootContextParameterSet.Builder((ServerWorld)target.getWorld())).add(LootContextParameters.THIS_ENTITY, target)
                    .add(LootContextParameters.ORIGIN, target.getPos()).add(LootContextParameters.DAMAGE_SOURCE, damageSource)
                    .addOptional(LootContextParameters.KILLER_ENTITY, damageSource.getAttacker())
                    .addOptional(LootContextParameters.DIRECT_KILLER_ENTITY, damageSource.getSource());

            LootContextParameterSet lootContextParameterSet = builder.build(LootContextTypes.ENTITY);
            lootTable.generateLoot(lootContextParameterSet, target.getLootTableSeed(), target::dropStack);
        }
    }
}
