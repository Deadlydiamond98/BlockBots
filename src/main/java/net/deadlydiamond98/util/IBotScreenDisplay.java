package net.deadlydiamond98.util;

import net.deadlydiamond98.BlockBots;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;

public interface IBotScreenDisplay<T> {

    default int getScreenAlpha(T entity) {
        return 25;
    }

    // HELPER

    default String getTypePath() {
        return this instanceof BlockEntity ? "block" : "entity";
    }
    default Identifier getScreen(String type) {
        return new Identifier(BlockBots.MOD_ID, "textures/screen/" + getTypePath() + "/" + type + ".png");
    }
    default Identifier getEye(String type) {
        return new Identifier(BlockBots.MOD_ID, "textures/screen/face/eye/" + type + ".png");
    }
    default Identifier getMouth(String type) {
        return new Identifier(BlockBots.MOD_ID, "textures/screen/face/mouth/" + type + ".png");
    }

    // SCREEN

    default Identifier getOnTexture() {
        return getScreen("powered");
    }

    default Identifier getOffTexture() {
        return getScreen("off");
    }

    // EYES

    default Identifier getEyeTexture(T entity) {
        return getEye(isBlinking(entity) ? "blink" : "regular");
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
        return getMouth("line");
    }

    default boolean showMouth(T entity) {
        return false;
    }
}
