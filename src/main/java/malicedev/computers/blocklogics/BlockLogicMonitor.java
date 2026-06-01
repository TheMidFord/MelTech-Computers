package malicedev.computers.blocklogics;
import malicedev.computers.screens.ScreenMonitor;
import malicedev.computers.tileentities.TileEntityMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.Random;
import static malicedev.computers.tileentities.TileEntityMonitor.resolution_width;

public class BlockLogicMonitor extends BlockLogicRotatable {
	public BlockLogicMonitor(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit) {
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_ARROW.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(x,y,z));
			for (int i =0;i<te.VRAM.length*8;i++) {
				if (i%2==0) {
					te.setVRAMBit(i, true);
				}
				else if (i%2 !=0){
					te.setVRAMBit(i,false);
				}
			}

		}
		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_ARROW_PURPLE.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(x,y,z));
			te.instruction = 0x01;



		}
		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_CHARGE_EXPLOSIVE.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(x,y,z));
			for (int i =0;i<te.VRAM.length*8;i++) {

					te.setVRAMBit(i,false);

			}

		}
		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_ARROW_GOLD.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(x,y,z));
			Random r = new Random();
				for (int i = 0; i < te.VRAM.length; i++) {
					te.VRAM[i] = (byte) r.nextInt(256);
				}
		}
		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_SNOWBALL.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(x,y,z));
			te.isFilling = !te.isFilling;
		}
		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_FIREBALL.id) {
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(x, y, z));
			for (int x1 = 0; x1 < resolution_width; x1++) {
				for (int y1 = 0; y1 < te.resolution_height; y1++) {
					te.setVRAMBit(y1 * resolution_width + x1, ((x1 + y1) & 0b1) != 0);
				}
			}
		}

		else{
		Minecraft.getMinecraft().displayScreen(new ScreenMonitor((TileEntityMonitor) world.getTileEntity(x,y,z)));
		}
		return super.onBlockRightClicked(world, x, y, z, player, side, xHit, yHit);
	}


}
