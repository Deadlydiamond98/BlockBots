package net.deadlydiamond98.common.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.SpawnEggItem;

public class BotSpawnEgg extends SpawnEggItem {
    public BotSpawnEgg(EntityType<? extends MobEntity> type, Settings settings) {
        super(type, 0xFFFFFF, 0xFFFFFF, settings);
    }

    @Override
    public String getTranslationKey() {
        return getEntityType(this.getDefaultStack().getNbt()).getTranslationKey();
    }
}
