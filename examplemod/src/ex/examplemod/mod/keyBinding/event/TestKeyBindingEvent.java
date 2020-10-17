package ex.examplemod.mod.keyBinding.event;

import ex.examplemod.mod.ExampleMod;
import ex.examplemod.mod.keyBinding.ExampleModKeyBindings;
import net.mcmaker.keyBindingEvent.KeyBindingEvent;
import net.minecraft.client.Minecraft;

public class TestKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		while (ExampleModKeyBindings.TEST_KEY_BINDING.isPressed()) {
			ExampleMod.GAMMA_LIGHT = !ExampleMod.GAMMA_LIGHT;
		}
		if (!ExampleMod.GAMMA_LIGHT) {
			minecraft.gameSettings.gamma = ExampleMod.GAMMA;
		} else {
			minecraft.gameSettings.gamma = 9.0;
		}
	}

}