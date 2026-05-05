package malicedev.computers.blocklogics;

import malicedev.computers.screens.ScreenMonitor;
import malicedev.computers.tileentities.TileEntityMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockLogicMonitor extends BlockLogicRotatable {
	public BlockLogicMonitor(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
		Minecraft.getMinecraft().displayScreen(new ScreenMonitor((TileEntityMonitor) world.getTileEntity(x,y,z)));
		return super.onBlockRightClicked(world, x, y, z, player, side, xHit, yHit);
	}
}
