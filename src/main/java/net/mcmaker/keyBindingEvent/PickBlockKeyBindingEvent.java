package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;

public class PickBlockKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		if (minecraft.player.isHandActive()) {
			while (minecraft.gameSettings.keyBindPickBlock.isPressed()) {
			}
		} else {
			while (minecraft.gameSettings.keyBindPickBlock.isPressed()) {
				minecraft.middleClickMouse();
			}
		}
	}

}
