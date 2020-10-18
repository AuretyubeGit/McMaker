package net.mcmaker.registry;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class NewRecipeRegistry {
	
	public static void registerRecipeTypeAndSerializer(IRecipeType<?> recipeType, IRecipeSerializer<?> recipeSerializer, ResourceLocation key) {
		registerRecipeType(recipeType, key);
		registerRecipeSerializer(recipeSerializer, key);
	}

	public static IRecipeSerializer<?> registerRecipeSerializer(IRecipeSerializer<?> recipeSerializer, ResourceLocation key) {
		return Registry.register(Registry.RECIPE_SERIALIZER, key, recipeSerializer);
	}

	public static IRecipeType<?> registerRecipeType(IRecipeType<?> recipeType, ResourceLocation key) {
		return Registry.register(Registry.RECIPE_TYPE, key, recipeType);
	}
	
}