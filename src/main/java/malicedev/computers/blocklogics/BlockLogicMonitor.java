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
import net.minecraft.core.world.pos.TilePosc;

import java.util.Random;

public class BlockLogicMonitor extends BlockLogicRotatable {
	public BlockLogicMonitor(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean onInteracted(World world, TilePosc tilePos, Player player, Side side, double xHit, double yHit) {
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_ARROW.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(tilePos));
			for (int i =0;i<(te.VRAM.length*8)-64;i++) {
				if (i%2==0) {
					te.setVRAMBit(i, true);
				}
				else if (i%2 !=0){
					te.setVRAMBit(i,false);
				}
			}

		}

		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_CHARGE_EXPLOSIVE.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(tilePos));
			for (int i =0;i<(te.VRAM.length*8)-64;i++) {

					te.setVRAMBit(i,false);

			}

		}
		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_ARROW_GOLD.id){
			TileEntityMonitor te = ((TileEntityMonitor) world.getTileEntity(tilePos));
			Random r = new Random();
				for (int i = 0; i < (te.VRAM.length)-8; i++) {
					te.VRAM[i] = (byte) r.nextInt(256);
				}
		}


		else{
		Minecraft.getMinecraft().displayScreen(new ScreenMonitor((TileEntityMonitor) world.getTileEntity(tilePos)));
		}
		return true;
	}


}
