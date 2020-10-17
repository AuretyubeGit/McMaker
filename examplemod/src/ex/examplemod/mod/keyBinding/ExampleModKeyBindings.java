package ex.examplemod.mod.keyBinding;

import org.lwjgl.glfw.GLFW;

import ex.examplemod.mod.ExampleMod;
import net.mcmaker.registry.NewRegistry;
import net.minecraft.client.settings.KeyBinding;

public class ExampleModKeyBindings {
	
	public static final String EXAMPLEMOD_CATEGORY = "category." + ExampleMod.MODID + ".examplemod";
	
	public static final KeyBinding TEST_KEY_BINDING = new KeyBinding("key." + ExampleMod.MODID + ".test", GLFW.GLFW_KEY_K, EXAMPLEMOD_CATEGORY.toString());
	
	public static void registerKeyBindingsCategories() {
		NewRegistry.registerKeyBindingCategory(EXAMPLEMOD_CATEGORY);
	}

	public static void registerKeyBindings() {
		NewRegistry.registerKeyBinding(TEST_KEY_BINDING);
	}

}