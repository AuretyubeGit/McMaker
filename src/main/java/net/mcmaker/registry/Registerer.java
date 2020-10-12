package net.mcmaker.registry;

import com.mojang.datafixers.types.Type;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraft.util.registry.Registry;

public class Registerer {

	public static Item registerItem(Item item, ResourceLocation resourceLocation) {
		return registerItem(resourceLocation, item);
	}

	public static Block registerBlock(Block block, ResourceLocation resourceLocation) {
		return Registry.register(Registry.BLOCK, resourceLocation, block);
	}
	
	public static <T extends TileEntity> TileEntityType<T> registerTileEntityType(TileEntityType.Builder<T> builder, ResourceLocation resourceLocation) {
		return registerTileEntity(resourceLocation.toString(), builder);
	}

	private static Item registerItem(ResourceLocation key, Item itemIn) {
		if (itemIn instanceof BlockItem) {
			((BlockItem) itemIn).addToBlockToItemMap(Item.BLOCK_TO_ITEM, itemIn);
		}
		return Registry.register(Registry.ITEM, key, itemIn);
	}

	private static <T extends TileEntity> TileEntityType<T> registerTileEntity(String key, TileEntityType.Builder<T> builder) {
		if (builder.blocks.isEmpty()) {
			System.err.println("Block entity type " + key + " requires at least one valid block to be defined!");
		}

		Type<?> type = Util.func_240976_a_(TypeReferences.BLOCK_ENTITY, key);
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, key, builder.build(type));
	}

}