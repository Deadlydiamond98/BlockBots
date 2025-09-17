package net.deadlydiamond98.mixin;

import net.deadlydiamond98.util.interfaces.IDownfall;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Biome.class)
public class BiomeMixin {


//    @Shadow @Final private Biome.Weather weather;
//
//    @Override
//    public float getDownfall() {
//        return this.weather;
//    }
}
