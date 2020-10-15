package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Hand;

public class DropKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		while (minecraft.gameSettings.keyBindDrop.isPressed()) {
			if (!minecraft.player.isSpectator() && minecraft.player.drop(Screen.func_231172_r_())) {
				minecraft.player.swingArm(Hand.MAIN_HAND);
			}
		}
	}

}
