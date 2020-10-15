package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.player.ChatVisibility;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class ChatKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		boolean flag2 = minecraft.gameSettings.chatVisibility != ChatVisibility.HIDDEN;
		if (flag2) {
			while (minecraft.gameSettings.keyBindChat.isPressed()) {
				func_238207_b_(minecraft, "");
			}

			if (minecraft.currentScreen == null && minecraft.loadingGui == null && minecraft.gameSettings.keyBindCommand.isPressed()) {
				func_238207_b_(minecraft, "/");
			}
		}
	}
	
	private void func_238207_b_(Minecraft minecraft, String p_238207_1_) {
		if (!minecraft.isIntegratedServerRunning() && !minecraft.func_238217_s_()) {
			if (minecraft.player != null) {
				minecraft.player.sendMessage(
						(new TranslationTextComponent("chat.cannotSend")).func_240699_a_(TextFormatting.RED),
						Util.field_240973_b_);
			}
		} else {
			minecraft.displayGuiScreen(new ChatScreen(p_238207_1_));
		}

	}

}
