package malicedev.computers.blocklogics;

import malicedev.computers.screens.ScreenMonitor;
import malicedev.computers.tileentities.TileEntityComputer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;

import java.util.Random;

public class BlockLogicMonitor extends BlockLogicRotatable {
	public BlockLogicMonitor(Block<?> block, Material material) {
		super(block, material);
	}
	@Override
	public boolean onInteracted(World world, TilePosc tilePos, Player player, Side side, double xHit, double yHit) {
		TilePos compTilePos = tilePos.down(new TilePos());
			Minecraft.getMinecraft().displayScreen(new ScreenMonitor((TileEntityComputer) world.getTileEntity(compTilePos)));

		return true;
	}
}
