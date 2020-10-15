package ex.examplemod.mod.tile;

import ex.examplemod.mod.ExampleMod;
import ex.examplemod.mod.block.ExampleModBlocks;
import net.mcmaker.registry.NewRegistry;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

public class ExampleModTileEntityTypes {
	
	public static TileEntityType<QuarryTileEntity> QUARRY = null;

	public static void registerTypes() {
		QUARRY =  NewRegistry.registerTileEntityType(TileEntityType.Builder.create(QuarryTileEntity::new, ExampleModBlocks.QUARRY), new ResourceLocation(ExampleMod.MODID, "quarry"));
	}
}
