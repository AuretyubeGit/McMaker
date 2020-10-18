package ex.examplemod.mod.recipes;

import javax.annotation.Nonnull;

import ex.examplemod.mod.ExampleMod;
import net.mcmaker.utils.recipe.RecipeWrapper;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IExampleRecipe extends IRecipe<RecipeWrapper>{
	
	ResourceLocation RECIPE_ID = new ResourceLocation(ExampleMod.MODID, "example");
	
	@Nonnull
	@Override
	default IRecipeType<?> getType() {
		return Registry.RECIPE_TYPE.getOrDefault(RECIPE_ID);
	}
	
	@Override
	default boolean canFit(int width, int height) {
		return false;
	}
	
	Ingredient getInput();
}