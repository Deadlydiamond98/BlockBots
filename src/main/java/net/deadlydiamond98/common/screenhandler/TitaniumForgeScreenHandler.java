package net.deadlydiamond98.common.screenhandler;

import net.deadlydiamond98.common.blocks.entities.crafting.TitaniumForgeBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public class TitaniumForgeScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    protected final World world;
    public final TitaniumForgeBlockEntity blockEntity;

    public TitaniumForgeScreenHandler(int syncID, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncID, playerInventory, playerInventory.player.getWorld().getBlockEntity(buf.readBlockPos()));
    }

    public TitaniumForgeScreenHandler(int syncID, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(BlockBotsScreenHandlers.TITANIUM_FORGE, syncID);

        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);
        this.world = playerInventory.player.getWorld();
        this.blockEntity = (TitaniumForgeBlockEntity) blockEntity;

        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new Slot(inventory, 1, 116, 35));

        // INVENTORY

        int i;
        int j;

        for(i = 0; i < 3; ++i) {
            for(j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
