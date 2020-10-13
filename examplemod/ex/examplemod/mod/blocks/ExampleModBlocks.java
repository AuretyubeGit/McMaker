package ex.examplemod.mod.blocks;

import java.util.Map;

import com.google.common.collect.Maps;

import ex.examplemod.mod.ExampleMod;
import net.mcmaker.registry.NewRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ExampleModBlocks {
	
	public static final Map<BlockItem, ResourceLocation> BLOCK_ITEMS = Maps.newHashMap();
	
	public static final ArtisticBlock ARTISTIC_BLOCK = new ArtisticBlock(Properties.from(Blocks.STONE));
	public static final QuarryBlock QUARRY = new QuarryBlock(AbstractBlock.Properties.create(Material.IRON));
	
	public static void registerBlocks() {
		NewRegistry.registerBlock(ARTISTIC_BLOCK, new ResourceLocation(ExampleMod.MODID, "artistic_block"));
		NewRegistry.registerBlock(QUARRY, new ResourceLocation(ExampleMod.MODID, "quarry"));
	}

	public static void registerBlockItems() {
		BLOCK_ITEMS.put(new BlockItem(ARTISTIC_BLOCK, new Item.Properties().group(ItemGroup.FOOD)), new ResourceLocation(ExampleMod.MODID, "artistic_block"));
		BLOCK_ITEMS.put(new BlockItem(QUARRY, new Item.Properties().group(ItemGroup.REDSTONE)), new ResourceLocation(ExampleMod.MODID, "quarry"));
		
		BLOCK_ITEMS.forEach((blockItem, resourceLocation) -> NewRegistry.registerItem(blockItem, resourceLocation));
	}
	
}