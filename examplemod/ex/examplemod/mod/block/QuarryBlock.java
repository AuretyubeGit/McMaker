package ex.examplemod.mod.block;

import javax.annotation.Nullable;

import ex.examplemod.mod.tile.QuarryTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class QuarryBlock extends Block implements ITileEntityProvider {

	public QuarryBlock(Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable TileEntity createNewTileEntity(IBlockReader arg0) {
		return new QuarryTileEntity();
	}
	
}