package net.deadlydiamond98.common.blocks.entities;

import net.deadlydiamond98.util.IMachineInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public abstract class AbstractCraftingBotBlockEntity extends AbstractBotBlockEntity implements ExtendedScreenHandlerFactory, IMachineInventory {
    protected final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(getSlots(), ItemStack.EMPTY);

    public AbstractCraftingBotBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(Objects.requireNonNull(Registries.BLOCK_ENTITY_TYPE.getId(this.getType())).toTranslationKey());
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    protected abstract int getSlots();
}
