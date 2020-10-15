package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;

public class SaveAndLoadToolBarKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		for (int i = 0; i < 9; ++i) {
			boolean flag = minecraft.gameSettings.keyBindSaveToolbar.isKeyDown();
			boolean flag1 = minecraft.gameSettings.keyBindLoadToolbar.isKeyDown();
			if (minecraft.gameSettings.keyBindsHotbar[i].isPressed()) {
				if (minecraft.player.isSpectator()) {
					minecraft.ingameGUI.getSpectatorGui().onHotbarSelected(i);
				} else if (!minecraft.player.isCreative() || minecraft.currentScreen != null || !flag1 && !flag) {
					minecraft.player.inventory.currentItem = i;
				} else {
					CreativeScreen.handleHotbarSnapshots(minecraft, i, flag1, flag);
				}
			}
		}
	}

}
