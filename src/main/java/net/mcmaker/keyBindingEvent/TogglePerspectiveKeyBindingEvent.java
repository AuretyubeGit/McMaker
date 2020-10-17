package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.PointOfView;

public class TogglePerspectiveKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		for (; minecraft.gameSettings.keyBindTogglePerspective.isPressed(); minecraft.worldRenderer
				.setDisplayListEntitiesDirty()) {
			PointOfView pointofview = minecraft.gameSettings.getFisrtPersonView();
			minecraft.gameSettings.isFirstPerson(minecraft.gameSettings.getFisrtPersonView().func_243194_c());
			if (pointofview.func_243192_a() != minecraft.gameSettings.getFisrtPersonView().func_243192_a()) {
				minecraft.gameRenderer.loadEntityShader(
						minecraft.gameSettings.getFisrtPersonView().func_243192_a() ? minecraft.getRenderViewEntity() : null);
			}
		}
	}
}