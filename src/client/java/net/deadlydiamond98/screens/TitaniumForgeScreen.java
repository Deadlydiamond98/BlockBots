package net.deadlydiamond98.screens;

import net.deadlydiamond98.BlockBots;
import net.deadlydiamond98.common.screenhandler.TitaniumForgeScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TitaniumForgeScreen extends HandledScreen<TitaniumForgeScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(BlockBots.MOD_ID, "textures/gui/titanium_forge.png");

    public TitaniumForgeScreen(TitaniumForgeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = this.x;
        int j = this.y;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }
}
