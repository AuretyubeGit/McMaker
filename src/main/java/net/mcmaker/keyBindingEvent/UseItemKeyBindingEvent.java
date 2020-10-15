package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;

public class UseItemKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		if (minecraft.player.isHandActive()) {
			if (!minecraft.gameSettings.keyBindUseItem.isKeyDown()) {
				minecraft.playerController.onStoppedUsingItem(minecraft.player);
			}
			while (minecraft.gameSettings.keyBindUseItem.isPressed()) {
			}
		} else {
			while (minecraft.gameSettings.keyBindUseItem.isPressed()) {
				minecraft.rightClickMouse();
			}
		}
		if (minecraft.gameSettings.keyBindUseItem.isKeyDown() && minecraft.rightClickDelayTimer == 0
				&& !minecraft.player.isHandActive()) {
			minecraft.rightClickMouse();
		}
	}

}
