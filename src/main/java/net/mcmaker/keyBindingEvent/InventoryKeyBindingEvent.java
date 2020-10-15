package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;

public class InventoryKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		while (minecraft.gameSettings.keyBindInventory.isPressed()) {
			if (minecraft.playerController.isRidingHorse()) {
				minecraft.player.sendHorseInventory();
			} else {
				minecraft.tutorial.openInventory();
				minecraft.displayGuiScreen(new InventoryScreen(minecraft.player));
			}
		}
	}

}
