package ex.examplemod.mod.block;

import java.util.Map;

import com.google.common.collect.Maps;

import ex.examplemod.mod.ExampleMod;
import net.mcmaker.registry.NewRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ExampleModBlocks {
	
	public static final Map<BlockItem, ResourceLocation> BLOCK_ITEMS = Maps.newHashMap();
	
	public static final QuarryBlock QUARRY = new QuarryBlock(AbstractBlock.Properties.create(Material.IRON));
	
	public static void registerBlocks() {
		NewRegistry.registerBlock(QUARRY, new ResourceLocation(ExampleMod.MODID, "quarry"));
	}

	public static void registerBlockItems() {
		BLOCK_ITEMS.put(new BlockItem(QUARRY, new Item.Properties().group(ItemGroup.REDSTONE)), new ResourceLocation(ExampleMod.MODID, "quarry"));
		
		BLOCK_ITEMS.forEach((blockItem, resourceLocation) -> NewRegistry.registerItem(blockItem, resourceLocation));
	}
	
}