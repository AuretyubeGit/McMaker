package ex.examplemod.mod;

import ex.examplemod.mod.block.ExampleModBlocks;
import ex.examplemod.mod.commands.ExampleModCommands;
import ex.examplemod.mod.item.ExampleModItems;
import ex.examplemod.mod.keyBinding.ExampleModKeyBindings;
import ex.examplemod.mod.keyBinding.event.ExampleModKeyBindingEvents;
import ex.examplemod.mod.tile.ExampleModTileEntityTypes;
import net.mcmaker.mod.Mod;
import net.minecraft.client.Minecraft;

@Mod(modid = ExampleMod.MODID)
public class ExampleMod {
	
	public static final String MODID = "examplemod";
	public static boolean GAMMA_LIGHT = false;
	public static double GAMMA;

	public ExampleMod() {
		GAMMA = Minecraft.getInstance().gameSettings.gamma;
		ExampleModItems.registerItems();
		ExampleModBlocks.registerBlocks();
		ExampleModBlocks.registerBlockItems();
		ExampleModTileEntityTypes.registerTypes();
		ExampleModKeyBindings.registerKeyBindingsCategories();
		ExampleModKeyBindings.registerKeyBindings();
		ExampleModKeyBindingEvents.registerKeyBindingEvents();
		ExampleModCommands.registerCommands();
	}
	
}