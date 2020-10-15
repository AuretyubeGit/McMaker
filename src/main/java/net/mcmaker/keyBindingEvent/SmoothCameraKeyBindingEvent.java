package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;

public class SmoothCameraKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		while (minecraft.gameSettings.keyBindSmoothCamera.isPressed()) {
			minecraft.gameSettings.smoothCamera = !minecraft.gameSettings.smoothCamera;
		}
	}

}
