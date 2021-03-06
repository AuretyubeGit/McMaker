package net.mcmaker.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;

public class NewWorldRegistry {

	public static Biome registerBiome(Biome biome, ResourceLocation resourceLocation) {
		return Registry.register(WorldGenRegistries.BIOME, resourceLocation, biome);
	}

}