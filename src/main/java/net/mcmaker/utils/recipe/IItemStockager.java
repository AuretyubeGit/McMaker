package net.mcmaker.utils.recipe;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public interface IItemStockager {

	int getSlots();

	@Nonnull
	ItemStack getStackInSlot(int paramInt);

	@Nonnull
	ItemStack insertItem(int paramInt, @Nonnull ItemStack paramItemStack, boolean paramBoolean);

	@Nonnull
	ItemStack extractItem(int paramInt1, int paramInt2, boolean paramBoolean);

	int getSlotMax(int paramInt);

	boolean isItemValid(int paramInt, @Nonnull ItemStack paramItemStack);
	
	void setStackInSlot(int paramInt, @Nonnull ItemStack paramItemStack);

}