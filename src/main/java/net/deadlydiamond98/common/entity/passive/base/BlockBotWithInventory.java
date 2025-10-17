package net.deadlydiamond98.common.entity.passive.base;

import net.deadlydiamond98.common.entity.base.BaseBlockBotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class BlockBotWithInventory extends BaseBlockBotEntity implements InventoryOwner {

    private final SimpleInventory inventory = new SimpleInventory(9);

    public BlockBotWithInventory(EntityType<? extends BaseBlockBotEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean canPickUpLoot() {
        return true;
    }

    @Override
    public boolean canGather(ItemStack stack) {
        return this.getInventory().canInsert(stack);
    }

    public boolean attemptGather(ItemStack stack) {
        return true;
    }

    @Override
    protected void loot(ItemEntity item) {
        InventoryOwner.pickUpItem(this, this, item);
    }

    @Override
    public SimpleInventory getInventory() {
        return this.inventory;
    }

    @Override
    protected void dropInventory() {
        super.dropInventory();
        this.inventory.clearToList().forEach(this::dropStack);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        this.writeInventory(nbt);
        return super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.readInventory(nbt);
    }

    public boolean canDepositToChest() {
        return !getInventory().isEmpty();
    }

    public boolean shouldHoldOntoStack(ItemStack mobStack) {
        return false;
    }
}
