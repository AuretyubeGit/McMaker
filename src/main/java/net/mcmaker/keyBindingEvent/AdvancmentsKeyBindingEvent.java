package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.AdvancementsScreen;

public class AdvancmentsKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		while (minecraft.gameSettings.keyBindAdvancements.isPressed()) {
			minecraft.displayGuiScreen(new AdvancementsScreen(minecraft.player.connection.getAdvancementManager()));
		}

	}

}
