package net.deadlydiamond98.common.items.battery;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DisposableBatteryItem extends Item implements IEnergyItem {

    public DisposableBatteryItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {

            ItemStack stack = user.getStackInHand(hand);

            int i = user.isSneaking() ? 1 : -1;
            if (depleteBattery(i, stack)) {
                user.sendMessage(Text.literal("Battery lvl is now: " + getBatteryLvl(stack)));
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public int getMaxCharge() {
        return 100;
    }
}