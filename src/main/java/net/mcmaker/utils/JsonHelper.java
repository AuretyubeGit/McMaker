package net.mcmaker.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class JsonHelper {

	public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

	public static ItemStack getItemStack(JsonObject jsonObject) {
		String itemId = JSONUtils.getString(jsonObject, "item");
		Item item = Registry.ITEM.getOrDefault(new ResourceLocation(itemId));
		if (item == null)
			if (jsonObject.has("nbt"))
				try {
					CompoundNBT nbt;
					JsonElement element = jsonObject.get("nbt");
					if (element.isJsonObject()) {
						nbt = JsonToNBT.getTagFromJson(GSON.toJson(element));
					} else {
						nbt = JsonToNBT.getTagFromJson(JSONUtils.getString(element, "nbt"));
					}
					CompoundNBT tmp = new CompoundNBT();
					tmp.put("tag", (INBT) nbt);
					tmp.putString("id", itemId);
					tmp.putInt("Count", JSONUtils.getInt(jsonObject, "count", 1));
					return ItemStack.read(tmp);
				} catch (CommandSyntaxException e) {
					throw new JsonSyntaxException("Invalid NBT Entry: " + e.toString());
				}
		return new ItemStack((IItemProvider) item, JSONUtils.getInt(jsonObject, "count", 1));
	}

}
