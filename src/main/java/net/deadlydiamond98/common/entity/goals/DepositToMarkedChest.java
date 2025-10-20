package net.deadlydiamond98.common.entity.goals;

import net.deadlydiamond98.common.blocks.BlockBotsBlocks;
import net.deadlydiamond98.common.entity.passive.base.BlockBotWithInventory;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class DepositToMarkedChest<T extends BlockBotWithInventory> extends MoveToTargetPosGoal {

    private boolean depositingToChest = false;
    private int timer = 0;
    private boolean closedChest = true;

    public DepositToMarkedChest(T mob, double speed, int range) {
        super(mob, speed, range);
    }

    @Override
    public boolean canStart() {
        return ((BlockBotWithInventory) this.mob).canDepositToChest() && super.canStart();
    }

    @Override
    public void start() {
        super.start();
        this.closedChest = true;
    }

    @Override
    public void stop() {
        super.stop();
        if (this.targetPos != null) {
            if (this.mob.getWorld().getBlockEntity(this.targetPos) instanceof Inventory inventory) {
                openShutChest(inventory, false);
            }
        }
        this.depositingToChest = false;
        this.timer = 0;
    }

    private SimpleInventory getInventory() {
        return ((BlockBotWithInventory) this.mob).getInventory();
    }

    @Override
    protected int getInterval(PathAwareEntity mob) {
        return 2;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.mob.getWorld().getBlockEntity(this.targetPos) instanceof Inventory chest) {
            double distance = this.mob.squaredDistanceTo(this.targetPos.getX() + 0.5f, this.targetPos.getY() + 0.5f, this.targetPos.getZ() + 0.5f);
            this.mob.getNavigation().startMovingTo(this.targetPos.getX() + 0.5f, this.targetPos.getY() - 1, this.targetPos.getZ() + 0.5f, 1.2);
            if (distance < Math.pow(this.mob.getWidth(), 2) + 3) {
                this.mob.getNavigation().stop();
                if (!this.depositingToChest) {
                    this.depositingToChest = true;
                    openShutChest(chest, true);
                }
                if (this.depositingToChest) {
                    this.timer++;
                    if (this.timer > 20) {
                        openShutChest(chest, false);

                        getInventory().stacks.forEach(mobStack -> {
                            int mobStackCount = mobStack.getCount();
                            if (((BlockBotWithInventory) this.mob).shouldHoldOntoStack(mobStack)) {
                                if (mobStackCount > 1) {
                                    mobStackCount = mobStackCount / 2;
                                } else {
                                    mobStackCount = 0;
                                }
                            }
                            for (int i = 0; i < chest.size(); i++) {
                                ItemStack chestStack = chest.getStack(i);
                                if (ItemEntity.canMerge(chestStack, mobStack)) {
                                    int maxInsertable = chestStack.getMaxCount() - chestStack.getCount();
                                    int countToInsert = Math.min(mobStackCount, maxInsertable);

                                    chestStack.increment(countToInsert);
                                    mobStack.decrement(countToInsert);
                                    chest.setStack(i, chestStack);
                                    break;
                                } else if (chestStack.isEmpty()) {
                                    ItemStack insertStack = mobStack.copy();
                                    insertStack.setCount(mobStackCount);
                                    chest.setStack(i, insertStack);
                                    mobStack.decrement(mobStackCount);
                                    break;
                                }
                            }
                        });
                        this.stop();
                    }
                }
            }
        }
    }

    public void openShutChest(Inventory blockEntity, boolean open) {
        if (blockEntity instanceof ChestBlockEntity chest) {
            if (open) {
                playSound(this.mob.getWorld(), this.targetPos, SoundEvents.BLOCK_CHEST_OPEN);
                this.mob.getWorld().addSyncedBlockEvent(this.targetPos, chest.getCachedState().getBlock(), 1, 1);
                this.closedChest = false;
            } else {
                if (!this.closedChest) {
                    playSound(this.mob.getWorld(), this.targetPos, SoundEvents.BLOCK_CHEST_CLOSE);
                    this.closedChest = true;
                }
                this.mob.getWorld().addSyncedBlockEvent(this.targetPos, chest.getCachedState().getBlock(), 1, 0);
            }
            this.mob.getWorld().updateNeighborsAlways(this.targetPos, chest.getCachedState().getBlock());
            this.mob.getWorld().updateNeighborsAlways(this.targetPos.down(), chest.getCachedState().getBlock());
        }
    }

    public void playSound(World world, BlockPos pos, SoundEvent soundEvent) {
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundEvent, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return targetChest(world, pos);
    }

    private boolean targetChest(WorldView world, BlockPos pos) {
        if (world.getBlockState(pos.up()).isOf(BlockBotsBlocks.CHEST_MARKER)) {
            if (world.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
                for (ItemStack entityStack : getInventory().stacks) {
                    if (!entityStack.isEmpty() && !((BlockBotWithInventory)this.mob).shouldHoldOntoStack(entityStack)) {
                        for (int i = 0; i < chest.inventory.size(); i++) {
                            ItemStack chestStack = chest.inventory.get(i);

                            if (chestStack.isEmpty() || (chestStack.getItem() == entityStack.getItem() && chestStack.getCount() < chestStack.getMaxCount())) {

                                int countToInsert = Math.min(entityStack.getCount(), entityStack.getMaxCount() - chestStack.getCount());

                                if (countToInsert > 0) {
                                    return true;
                                }
                            }
                        }
                    }
                }

                return false;
            }
        }
        return false;
    }
}
