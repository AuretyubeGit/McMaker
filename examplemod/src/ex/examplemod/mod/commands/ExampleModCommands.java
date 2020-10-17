package ex.examplemod.mod.commands;

import ex.examplemod.mod.ExampleMod;
import net.mcmaker.command.NewCommand;
import net.mcmaker.registry.NewRegistry;
import net.minecraft.util.ResourceLocation;

public class ExampleModCommands {
	
	public static final NewCommand GOOD_BYE = new GoodByeCommand();
	
	public static void registerCommands() {
		NewRegistry.registerCommand(GOOD_BYE, new ResourceLocation(ExampleMod.MODID, "goodbye"));
	}

}
