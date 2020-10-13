package ex.examplemod.mod;

import ex.examplemod.mod.blocks.ExampleModBlocks;
import ex.examplemod.mod.items.ExampleModItems;
import ex.examplemod.mod.tiles.ExampleModTileEntityTypes;
import net.mcmaker.mod.Mod;

@Mod(modid = ExampleMod.MODID)
public class ExampleMod {
	
	public static final String MODID = "examplemod";

	public ExampleMod() {
		ExampleModItems.registerItems();
		ExampleModBlocks.registerBlocks();
		ExampleModBlocks.registerBlockItems();
		ExampleModTileEntityTypes.registerTypes();
	}
	
}