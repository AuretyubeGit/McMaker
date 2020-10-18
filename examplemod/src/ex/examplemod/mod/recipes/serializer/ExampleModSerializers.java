package ex.examplemod.mod.recipes.serializer;

import ex.examplemod.mod.ExampleMod;
import ex.examplemod.mod.recipes.ExampleRecipe;
import net.mcmaker.registry.NewRecipeRegistry;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;

public class ExampleModSerializers {
	
	public static final IRecipeSerializer<ExampleRecipe> EXAMPLE_RECIPE_SERIALIZER = new ExampleRecipeSerializer();
	
	public static final ExampleRecipeSerializer EXAMPLE_SERIALIZER = (ExampleRecipeSerializer) NewRecipeRegistry.registerRecipeSerializer(EXAMPLE_RECIPE_SERIALIZER, new ResourceLocation(ExampleMod.MODID, "example"));
	
}