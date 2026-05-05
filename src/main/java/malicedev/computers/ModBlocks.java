package malicedev.computers;

import malicedev.computers.blocklogics.BlockLogicMonitor;
import malicedev.computers.tileentities.TileEntityMonitor;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;

import java.util.Random;

import static malicedev.computers.Main.MOD_ID;
import static malicedev.computers.Main.blockId;

public class ModBlocks {
	private  ModBlocks() {};


	public static Block<?> BlockMonitor;

	public static void init(){

		EntityHelper.createTileEntity(TileEntityMonitor.class, NamespaceID.getPermanent(MOD_ID,"monitor"));

		BlockMonitor = new BlockBuilder(MOD_ID)
			.setTileEntity(() -> {
				TileEntityMonitor te = new TileEntityMonitor();
				Random r = new Random();
				for (int i = 0; i < te.VRAM.length; i++) {
					te.VRAM[i] = (byte) r.nextInt(256);
				}
				return te;

			})
			.build("monitor","monitor",blockId++,(block) -> new BlockLogicMonitor(block, Material.glass));
	}

}
