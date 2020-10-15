package net.mcmaker.registry;

import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Maps;
import com.mojang.serialization.Lifecycle;

import net.mcmaker.keyBindingEvent.KeyBindingEvent;
import net.mcmaker.keyBindingEvent.KeyBindingEvents;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NewRegistries {

	private static final Map<ResourceLocation, Supplier<?>> REGISTRIES = Maps.newLinkedHashMap();
	private static final MutableRegistry<MutableRegistry<?>> MUTALBE_REGISTRY = new SimpleRegistry<>(
			RegistryKey.addRegistryKey(new ResourceLocation("root")), Lifecycle.experimental());

	public static final RegistryKey<Registry<KeyBindingEvent>> KEY_BINDING_EVENT_REGISTRY_KEY = RegistryKey
			.addRegistryKey(new ResourceLocation("examplemod", "keybinding_event"));

	public static final Registry<KeyBindingEvent> KEY_BINDING_EVENT = addRegistry(KEY_BINDING_EVENT_REGISTRY_KEY,
			() -> {
				return KeyBindingEvents.TOGGLE_PERSPECTIVE;
			});

	private static <T> Registry<T> addRegistry(RegistryKey<? extends Registry<T>> registryKey, Supplier<T> supplier) {
		return addRegistry(registryKey, Lifecycle.stable(), supplier);
	}

	private static <T> Registry<T> addRegistry(RegistryKey<? extends Registry<T>> registryKey, Lifecycle cylce,
			Supplier<T> supplier) {
		return addRegistry(registryKey, new SimpleRegistry<>(registryKey, cylce), supplier, cylce);
	}

	private static <T, R extends MutableRegistry<T>> R addRegistry(RegistryKey<? extends Registry<T>> p_243666_0_,
			R p_243666_1_, Supplier<T> supplier, Lifecycle lifecycle) {
		ResourceLocation resourcelocation = p_243666_0_.func_240901_a_();
		REGISTRIES.put(resourcelocation, supplier);
		MutableRegistry<R> mutableregistry = (MutableRegistry<R>) MUTALBE_REGISTRY;
		return (R) mutableregistry.register((RegistryKey) p_243666_0_, p_243666_1_, lifecycle);
	}

	public static <V, T extends V> T register(Registry<V> registry, ResourceLocation p_243664_1_, T p_243664_2_) {
		return ((MutableRegistry<V>) registry).register(RegistryKey.register(registry.func_243578_f(), p_243664_1_),
				p_243664_2_, Lifecycle.stable());
	}

	public static <V, T extends V> T register(Registry<V> registry, int index, RegistryKey<V> p_243662_2_,
			T p_243662_3_) {
		return ((MutableRegistry<V>) registry).register(index, p_243662_2_, p_243662_3_, Lifecycle.stable());
	}

	static {
		REGISTRIES.forEach((key, sup) -> {
			if (sup.get() == null) {
				System.err.println("Unable to bootstrap registry '" + key + "'");
			}
		});
		Registry.func_239738_a_(MUTALBE_REGISTRY);
		System.out.println("KeyBinding Event Initialized");
	}
}