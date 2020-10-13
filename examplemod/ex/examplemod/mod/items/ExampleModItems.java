package ex.examplemod.mod.items;

import net.mcmaker.registry.NewRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ExampleModItems {

	public static final TestItem TEST = new TestItem(new Item.Properties().maxStackSize(5).group(ItemGroup.REDSTONE));
	
	public static void registerItems() {
		NewRegistry.registerItem(TEST, new ResourceLocation("examplemod", "test"));
	}
	
}