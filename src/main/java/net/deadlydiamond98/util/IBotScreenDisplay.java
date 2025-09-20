package net.deadlydiamond98.util;

import net.deadlydiamond98.BlockBots;
import net.minecraft.util.Identifier;

public interface IBotScreenDisplay<T> {

    default int getScreenAlpha(T entity) {
        return 25;
    }

    // SCREEN

    default Identifier getOnTexture() {
        return new Identifier(BlockBots.MOD_ID,"textures/block/block_bot/screen/powered.png");
    }

    default Identifier getOffTexture() {
        return new Identifier(BlockBots.MOD_ID,"textures/block/block_bot/screen/off.png");
    }

    // EYES

    default Identifier getEyeTexture(T entity) {
        String type = isBlinking(entity) ? "blink" : "regular";
        return new Identifier(BlockBots.MOD_ID, "textures/face/eye/" + type + ".png");
    }

    default boolean eyesFollowPlayer(T entity) {
        return true;
    }

    default boolean isBlinking(T entity) {
        return getBlinkTimer(entity) % 60 <= 3;
    }

    int getBlinkTimer(T entity);

    // MOUTH

    default Identifier getMouthTexture(T entity) {
        return new Identifier(BlockBots.MOD_ID, "textures/face/mouth/line.png");
    }

    default boolean showMouth(T entity) {
        return false;
    }
}
