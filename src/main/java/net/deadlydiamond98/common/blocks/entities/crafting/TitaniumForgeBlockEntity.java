package net.deadlydiamond98.common.blocks.entities.crafting;

import net.deadlydiamond98.common.blocks.BlockBotsBlockEntities;
import net.deadlydiamond98.common.blocks.entities.AbstractBotBlockEntity;
import net.deadlydiamond98.common.blocks.entities.AbstractCraftingBotBlockEntity;
import net.deadlydiamond98.common.screenhandler.TitaniumForgeScreenHandler;
import net.deadlydiamond98.util.IMachineInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TitaniumForgeBlockEntity extends AbstractCraftingBotBlockEntity {

    public TitaniumForgeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockBotsBlockEntities.TITANIUM_FORGE, pos, state);
    }

    @Override
    protected int getSlots() {
        return 2;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new TitaniumForgeScreenHandler(syncId, playerInventory, this);
    }
}
