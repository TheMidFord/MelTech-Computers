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
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;

import java.util.Random;

public class BlockLogicComputer extends BlockLogicRotatable {
	public BlockLogicComputer(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public boolean onInteracted(World world, TilePosc tilePos, Player player, Side side, double xHit, double yHit) {
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_ARROW.id){
			TileEntityComputer te = ((TileEntityComputer) world.getTileEntity(tilePos));
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
			TileEntityComputer te = ((TileEntityComputer) world.getTileEntity(tilePos));
			for (int i =0;i<(te.VRAM.length*8)-64;i++) {

					te.setVRAMBit(i,false);

			}

		}
		else if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Items.AMMO_ARROW_GOLD.id){
			TileEntityComputer te = ((TileEntityComputer) world.getTileEntity(tilePos));
			Random r = new Random();
				for (int i = 0; i < (te.VRAM.length)-8; i++) {
					te.VRAM[i] = (byte) r.nextInt(256);
				}
		}


		else{
	//	Minecraft.getMinecraft().displayScreen(new ScreenMonitor((TileEntityComputer) world.getTileEntity(tilePos)));
		}
		return true;
	}


}
