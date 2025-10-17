package net.deadlydiamond98.common.entity.passive.base;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.deadlydiamond98.common.entity.goals.ownergoal.BlockBotFollowOwnerGoal;
import net.deadlydiamond98.common.entity.goals.ownergoal.WanderAroundBlockBotGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class OwnedBlockBotEntity extends BlockBotWithInventory implements Tameable {

    public enum BehaviorState {
        FOLLOWING("follow"),
        WANDERING("wander"),
        STAYING("sit");

        private final String key;

        BehaviorState(String key) {
            this.key = key;
        }

        public Text getLang() {
            return Text.translatable("entity.block_bots.follow_state." + this.key);
        }
    }

    protected static final TrackedData<Optional<UUID>> OWNER_UUID = DataTracker.registerData(OwnedBlockBotEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    public BehaviorState followState = getDefaultFollowState();

    public OwnedBlockBotEntity(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(7, new WanderAroundBlockBotGoal(this, 1));

        this.goalSelector.add(1, new BlockBotFollowOwnerGoal(this, 1, 10, 2, false));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            this.followState = BehaviorState.values()[(this.followState.ordinal() + 1) % BehaviorState.values().length];
            player.sendMessage(Text.translatable("entity.block_bots.follow_state")
                    .append(this.followState.getLang()), true);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getOwner() == null && !getWorld().isClient()) {
            PlayerEntity player = this.getWorld().getClosestPlayer(this, 20);
            if (player != null) {
                this.setOwner(player);
            }
        }
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(OWNER_UUID, Optional.empty());
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.getOwnerUuid() != null) {
            nbt.putUuid("Owner", this.getOwnerUuid());
        }

        nbt.putInt("FollowState", this.followState.ordinal());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        UUID uUID;
        if (nbt.containsUuid("Owner")) {
            uUID = nbt.getUuid("Owner");
        } else {
            String string = nbt.getString("Owner");
            uUID = ServerConfigHandler.getPlayerUuidByName(this.getServer(), string);
        }

        if (uUID != null) {
            try {
                this.setOwnerUuid(uUID);
            } catch (Throwable ignored) {}
        }

        this.followState = BehaviorState.values()[nbt.getInt("FollowState")];
    }

    @Nullable
    public UUID getOwnerUuid() {
        return (UUID)((Optional)this.dataTracker.get(OWNER_UUID)).orElse(null);
    }

    @Override
    public EntityView method_48926() {
        return this.getWorld();
    }

    public void setOwnerUuid(@Nullable UUID uuid) {
        this.dataTracker.set(OWNER_UUID, Optional.ofNullable(uuid));
    }

    public void setOwner(PlayerEntity player) {
        this.setOwnerUuid(player.getUuid());
    }

    public BehaviorState getDefaultFollowState() {
        return BehaviorState.WANDERING;
    }
}
