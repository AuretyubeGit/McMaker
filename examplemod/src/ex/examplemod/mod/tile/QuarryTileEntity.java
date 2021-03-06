package ex.examplemod.mod.tile;

import javax.annotation.Nullable;

import ex.examplemod.mod.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {

	public int x, y, z, tick;
	boolean initialized = false;

	public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public QuarryTileEntity() {
		this(ExampleModTileEntityTypes.QUARRY);
	}

	@Override
	public void tick() {
		if (!initialized)
			init();
		tick++;
		if (tick == 40) {
			tick = 0;
			if (y > 2)
				execute();
		}
	}

	private void init() {
		initialized = true;
		x = this.pos.getX() - 1;
		y = this.pos.getY() - 1;
		z = this.pos.getZ() - 1;
		tick = 0;
	}

	private void execute() {
		int index = 0;
		Block[] blocksRemoved = new Block[9];
		for (int x = 0; x < 3; x++) {
			for (int z = 0; z < 3; z++) {
				BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
				blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
				destroyBlock(posToBreak, true, null);
				index++;
			}
		}
		this.y--;
	}

	private boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
		BlockState blockstate = world.getBlockState(pos);
		if (blockstate.isAir())
			return false;
		else {
			FluidState ifluidstate = world.getFluidState(pos);
			world.playEvent(2001, pos, Block.getStateId(blockstate));
			if (dropBlock) {
				TileEntity tileentity = (blockstate instanceof ITileEntityProvider) ? world.getTileEntity(pos) : null;
				Block.spawnDrops(blockstate, world, this.pos.add(0, 1.5, 0), tileentity, entity, ItemStack.EMPTY);
			}
			return world.setBlockState(pos, ifluidstate.getBlockState(), 3);
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("InitValues", NBTHelper.toNBT(this));
		return super.write(compound);
	}
	
	@Override
	public void read(BlockState state, CompoundNBT compoundNBT) {
		super.read(state, compoundNBT);
		CompoundNBT compound = compoundNBT.getCompound("InitValues");
		if(compound != null) {
			this.x = compound.getInt("x");
			this.y = compound.getInt("y");
			this.z = compound.getInt("z");
			this.tick = 0;
			this.initialized = true;
			return;
		}
		init();
	}
}