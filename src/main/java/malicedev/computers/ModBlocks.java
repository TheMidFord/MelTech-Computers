package malicedev.computers;

import malicedev.computers.blocklogics.BlockLogicComputer;
import malicedev.computers.blocklogics.BlockLogicMonitor;
import malicedev.computers.tileentities.TileEntityComputer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;

import static malicedev.computers.Main.MOD_ID;
import static malicedev.computers.Main.blockId;

public class ModBlocks {
	private  ModBlocks() {};


	public static Block<?> BlockComputer;
	public static Block<?> BlockMonitor;

	public static void init(){
		EntityHelper.addMapping(TileEntityComputer.class,new NamespaceID(MOD_ID,"computer"));

		BlockComputer = new BlockBuilder(MOD_ID)
			.setTileEntity(() -> {
				TileEntityComputer te = new TileEntityComputer();
				return te;

			})
			.build("computer","computer",blockId++,(block) -> new BlockLogicComputer(block, Materials.METAL));
		BlockMonitor = new BlockBuilder(MOD_ID)
			.build("monitor","monitor",blockId++,(block)-> new BlockLogicMonitor(block,Materials.GLASS));
	}

}
