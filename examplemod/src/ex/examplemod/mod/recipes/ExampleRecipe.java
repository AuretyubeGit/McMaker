package ex.examplemod.mod.recipes;

import ex.examplemod.mod.recipes.serializer.ExampleModSerializers;
import net.mcmaker.utils.recipe.RecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ExampleRecipe implements IExampleRecipe {
	
	private final ResourceLocation id;
	private Ingredient input;
	private final ItemStack output;
	
	public ExampleRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
		this.id = id;
		this.input = input;
		this.output = output;
	}
	
	@Override
	public boolean canFit(int arg0, int arg1) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper arg0) {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ExampleModSerializers.EXAMPLE_SERIALIZER;
	}

	@Override
	public boolean matches(RecipeWrapper inventory, World world) {
		if(this.input.test(inventory.getStackInSlot(0))) {
			return true;
		}
		return false;
	}

	@Override
	public Ingredient getInput() {
		return this.input;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.from(null, this.input);
	}

}
