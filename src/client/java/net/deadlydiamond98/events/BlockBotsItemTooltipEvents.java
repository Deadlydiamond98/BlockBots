package net.deadlydiamond98.events;

import net.deadlydiamond98.common.items.battery.IEnergyItem;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class BlockBotsItemTooltipEvents {

    private static final Text TITLE = Text.translatable("item.block_bots.energy.title").formatted(Formatting.GRAY);
    private static int index = 1;

    public static void register() {
        ItemTooltipCallback.EVENT.register(BlockBotsItemTooltipEvents::batteryLevelTooltip);
    }

    private static void batteryLevelTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        if (stack.getItem() instanceof IEnergyItem battery) {
            int lvl = battery.getBatteryLvl(stack);
            int maxLvl = battery.getMaxCharge();

            String amount = lvl + " / " + maxLvl;
            int color = battery.getTextColor(stack);

            insertLine(lines, ScreenTexts.EMPTY);
            insertLine(lines, TITLE);
            insertLine(lines, ScreenTexts.space().append(Text.literal(amount)).setStyle(Style.EMPTY.withColor(color)));
            index = 1;
        }
    }

    private static void insertLine(List<Text> lines, Text text) {
        lines.add(index++, text);
    }
}
