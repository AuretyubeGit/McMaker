package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;

public class AttackKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		if (minecraft.player.isHandActive()) {
			while (minecraft.gameSettings.keyBindAttack.isPressed()) {
			}
		} else {
			while (minecraft.gameSettings.keyBindAttack.isPressed()) {
				minecraft.clickMouse();
			}
		}
		minecraft.sendClickBlockToController(minecraft.currentScreen == null
				&& minecraft.gameSettings.keyBindAttack.isKeyDown() && minecraft.mouseHelper.isMouseGrabbed());
	}

}