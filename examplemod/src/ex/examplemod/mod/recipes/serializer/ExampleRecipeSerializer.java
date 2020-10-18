package ex.examplemod.mod.recipes.serializer;

import com.google.gson.JsonObject;

import ex.examplemod.mod.recipes.ExampleRecipe;
import net.mcmaker.utils.JsonHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

public class ExampleRecipeSerializer implements IRecipeSerializer<ExampleRecipe> {

	@Override
	public ExampleRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = JsonHelper.getItemStack(JSONUtils.getJsonObject(json, "output"));
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
		return new ExampleRecipe(recipeId, input, output);
	}

	@Override
	public ExampleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		ItemStack output = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);
		return new ExampleRecipe(recipeId, input, output);
	}

	@Override
	public void write(PacketBuffer buffer, ExampleRecipe recipe) {
		Ingredient input = recipe.getIngredients().get(0);
		input.write(buffer);
		
		buffer.writeItemStack(recipe.getRecipeOutput());
	}

}