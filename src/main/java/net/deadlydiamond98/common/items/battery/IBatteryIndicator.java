package net.deadlydiamond98.common.items.battery;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.koalalib.common.items.other.IAnimatedSpriteIconItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface IBatteryIndicator extends IAnimatedSpriteIconItem {

    int getBatteryLvl(ItemStack stack);
    
    default int getBatteryIconLvl(ItemStack stack) {
        return stack.hasNbt() ? (int) Math.min(7, Math.floor(getBatteryLvl(stack) / 12.5)) : 7;
    }

    @Override
    default int getFrameTime(PlayerEntity playerEntity, ItemStack itemStack) {
        return 10;
    }

    @Override
    default int getFrames(PlayerEntity playerEntity, ItemStack stack) {
        return getBatteryIconLvl(stack) <= 0 ? 2 : 0;
    }
    
    @Override
    default Identifier getTexture(PlayerEntity player, ItemStack stack) {
        return new Identifier(BlockBots.MOD_ID, "textures/item/icon/battery_" + getBatteryIconLvl(stack) + ".png");
    }

    @Override
    default boolean showIcon(PlayerEntity player, ItemStack stack) {
        return getBatteryLvl(stack) > 0;
    }

    @Override
    default GUICorner getGUICorner() {
        return GUICorner.BOTTOM_RIGHT;
    }

    @Override
    default int pixelOffsetX() {
        return 2;
    }

    @Override
    default int pixelOffsetY() {
        return -2;
    }
}
