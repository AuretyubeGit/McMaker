package ex.examplemod.mod.keyBinding.event;

import ex.examplemod.mod.ExampleMod;
import net.mcmaker.keyBindingEvent.KeyBindingEvent;
import net.mcmaker.registry.NewRegistry;
import net.minecraft.util.ResourceLocation;

public class ExampleModKeyBindingEvents {
	
	public static final KeyBindingEvent TEST = new TestKeyBindingEvent();
	
	public static void registerKeyBindingEvents() {
		NewRegistry.registerKeyBindingEvent(TEST, new ResourceLocation(ExampleMod.MODID, "test"));
	}

}
