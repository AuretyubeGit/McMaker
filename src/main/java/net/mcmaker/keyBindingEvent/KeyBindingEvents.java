package net.mcmaker.keyBindingEvent;

import net.mcmaker.registry.NewRegistries;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class KeyBindingEvents {
	
	public static final KeyBindingEvent TOGGLE_PERSPECTIVE = register("toggle_perspective", new TogglePerspectiveKeyBindingEvent());
	public static final KeyBindingEvent SMOOTH_CAMERA = register("smooth_camera", new SmoothCameraKeyBindingEvent());
	public static final KeyBindingEvent SAVE_AND_LOAD_TOOLBAR = register("save_and_load_toolbar", new SaveAndLoadToolBarKeyBindingEvent());
	public static final KeyBindingEvent INVENTORY = register("inventory", new InventoryKeyBindingEvent());
	public static final KeyBindingEvent ADVANCMENTS = register("advancments", new AdvancmentsKeyBindingEvent());
	public static final KeyBindingEvent SWAP_HANDS = register("swap_hands", new SwapHandsKeyBindingEvent());
	public static final KeyBindingEvent DROP = register("drop", new DropKeyBindingEvent());
	public static final KeyBindingEvent CHAT = register("chat", new ChatKeyBindingEvent());
	public static final KeyBindingEvent USE_ITEM = register("use_item", new UseItemKeyBindingEvent());
	public static final KeyBindingEvent ATTACK = register("attack", new AttackKeyBindingEvent());
	public static final KeyBindingEvent PICK_BLOCK = register("pick_block", new PickBlockKeyBindingEvent());
	
	private static KeyBindingEvent register(String key, KeyBindingEvent keyBindingEvent) {
		return Registry.register(NewRegistries.KEY_BINDING_EVENT, new ResourceLocation(key), keyBindingEvent);
	}
	
}