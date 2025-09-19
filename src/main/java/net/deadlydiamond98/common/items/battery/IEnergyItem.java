package net.deadlydiamond98.common.items.battery;

import net.deadlydiamond98.util.TempColorUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public interface IEnergyItem extends IBatteryIndicator {
    int getMaxCharge();

    @Override
    default int getBatteryLvl(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("energy");
        return nbtCompound != null && nbtCompound.contains("BatteryLvl", 99) ? nbtCompound.getInt("BatteryLvl") : getMaxCharge();
    }

    default void setBatteryLvl(ItemStack stack, int lvl) {
        stack.getOrCreateSubNbt("energy").putInt("BatteryLvl", Math.max(0, Math.min(getMaxCharge(), lvl)));
    }

    default boolean depleteBattery(int i, ItemStack stack) {
        int newLvl = getBatteryLvl(stack) - i;
        if (i < 0) {
            return energizeBattery(-i, stack);
        } else if (newLvl >= 0) {
            setBatteryLvl(stack, newLvl);
            return true;
        }
        return false;
    }

    default boolean energizeBattery(int i, ItemStack stack) {
        int newLvl = getBatteryLvl(stack) + i;
        if (i < 0) {
            return depleteBattery(-i, stack);
        } else if (newLvl <= getMaxCharge()) {
            setBatteryLvl(stack, newLvl);
            return true;
        }
        return false;
    }

    default int getTextColor(ItemStack stack) {
        return TempColorUtil.blend(0xAD0000, 0x2BFF00, (float) getBatteryLvl(stack) / getMaxCharge());
    }
}
