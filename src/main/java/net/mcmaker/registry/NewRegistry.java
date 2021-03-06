package net.mcmaker.registry;

import org.apache.commons.lang3.ArrayUtils;

import com.mojang.datafixers.types.Type;

import net.mcmaker.command.NewCommand;
import net.mcmaker.keyBindingEvent.KeyBindingEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("resource")
public class NewRegistry {

	public static Item registerItem(Item item, ResourceLocation resourceLocation) {
		return registerItem(resourceLocation, item);
	}

	public static Block registerBlock(Block block, ResourceLocation resourceLocation) {
		return Registry.register(Registry.BLOCK, resourceLocation, block);
	}

	public static <T extends TileEntity> TileEntityType<T> registerTileEntityType(TileEntityType.Builder<T> builder,
			ResourceLocation resourceLocation) {
		return registerTileEntity(resourceLocation.toString(), builder);
	}

	public static Effect registerEffect(Effect effect, ResourceLocation key) {
		return Registry.register(Registry.EFFECTS, key, effect);
	}

	public static Potion registerPotion(Potion potion, ResourceLocation key) {
		return Registry.register(Registry.POTION, key, potion);
	}

	public static ContainerType<?> registerContainerType(ContainerType<? extends Container> containerType,
			ResourceLocation key) {
		return Registry.register(Registry.CONTAINER_TYPE, key, containerType);
	}

	public static SoundEvent registerSoundEvent(SoundEvent soundEvent, ResourceLocation key) {
		return Registry.register(Registry.SOUND_EVENT, key, soundEvent);
	}

	public static Enchantment registerEnchantment(Enchantment enchantment, ResourceLocation key) {
		return Registry.register(Registry.ENCHANTMENT, key, enchantment);
	}

	public static Fluid registerFluid(Fluid fluid, ResourceLocation key) {
		return Registry.register(Registry.FLUID, key, fluid);
	}

	public static <E extends Entity> EntityType<E> registerEntityType(EntityType<E> entityType, ResourceLocation key) {
		return Registry.register(Registry.ENTITY_TYPE, key, entityType);
	}

	public static void registerKeyBinding(KeyBinding keyBinding) {
		(Minecraft.getInstance().gameSettings.keyBindings) = (KeyBinding[]) ArrayUtils
				.add((Object[]) (Minecraft.getInstance()).gameSettings.keyBindings, keyBinding);
	}

	public static void registerKeyBindingCategory(String categoryKey) {
		int index = 8;
		KeyBinding.CATEGORY_ORDER.put(categoryKey, index);
		index++;
	}

	public static KeyBindingEvent registerKeyBindingEvent(KeyBindingEvent keyBindingEvent, ResourceLocation key) {
		return NewRegistries.register(NewRegistries.KEY_BINDING_EVENT, key, keyBindingEvent);
	}
	
	public static NewCommand registerCommand(NewCommand command, ResourceLocation key) {
		return NewRegistries.register(NewRegistries.COMMAND, key, command);
	}

	private static Item registerItem(ResourceLocation key, Item itemIn) {
		if (itemIn instanceof BlockItem) {
			((BlockItem) itemIn).addToBlockToItemMap(Item.BLOCK_TO_ITEM, itemIn);
		}
		return Registry.register(Registry.ITEM, key, itemIn);
	}

	private static <T extends TileEntity> TileEntityType<T> registerTileEntity(String key,
			TileEntityType.Builder<T> builder) {
		if (builder.blocks.isEmpty()) {
			System.err.println("Block entity type " + key + " requires at least one valid block to be defined!");
		}

		Type<?> type = Util.func_240976_a_(TypeReferences.BLOCK_ENTITY, key);
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, key, builder.build(type));
	}
}