package ex.examplemod.mod.recipes;

import net.mcmaker.registry.NewRecipeRegistry;
import net.mcmaker.utils.recipe.RecipeType;
import net.minecraft.item.crafting.IRecipeType;

@SuppressWarnings("unchecked")
public class ExampleModRecipeTypes {
	
	public static final IRecipeType<ExampleRecipe> EXAMPLE_RECIPE_TYPE = (IRecipeType<ExampleRecipe>) NewRecipeRegistry.registerRecipeType(new RecipeType<>(), IExampleRecipe.RECIPE_ID);
	
}