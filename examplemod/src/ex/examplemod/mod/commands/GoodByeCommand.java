package ex.examplemod.mod.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.mcmaker.command.NewCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class GoodByeCommand extends NewCommand {

	@Override
	public void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("goodbye").executes((command) -> {
			ClientPlayerEntity player = Minecraft.getInstance().player;
			player.setPosition(player.getPosX(), player.getPosY() + 200, player.getPosZ());
			return 1;
		}));
	}

}
