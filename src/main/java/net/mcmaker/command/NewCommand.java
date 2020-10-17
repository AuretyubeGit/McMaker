package net.mcmaker.command;

import com.mojang.brigadier.CommandDispatcher;

import net.mcmaker.registry.NewRegistries;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands.EnvironmentType;

public abstract class NewCommand {
	
	public abstract void register(CommandDispatcher<CommandSource> dispatcher);
	
	public EnvironmentType getSpecialEnvironmentType() {
		return EnvironmentType.ALL;
	}
	
	@Override
	public String toString() {
		return NewRegistries.COMMAND.getKey(this).toString();
	}
	
}