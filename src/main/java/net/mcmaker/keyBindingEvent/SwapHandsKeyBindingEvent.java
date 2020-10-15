package net.mcmaker.keyBindingEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPlayerDiggingPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class SwapHandsKeyBindingEvent extends KeyBindingEvent {

	@Override
	public void onKeyBindingEvent(Minecraft minecraft) {
		while (minecraft.gameSettings.keyBindSwapHands.isPressed()) {
			if (!minecraft.player.isSpectator()) {
				minecraft.getConnection().sendPacket(new CPlayerDiggingPacket(
						CPlayerDiggingPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN));
			}
		}
	}

}
