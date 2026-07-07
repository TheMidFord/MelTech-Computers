package malicedev.computers;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelHorizontalRotation;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.util.ModelEntrypoint;

import static net.minecraft.client.render.item.model.ItemModelDispatcher.*;

public class ModModels implements ModelEntrypoint {


	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(new BlockModelHorizontalRotation<>(ModBlocks.BlockComputer)
			.setAllTextures("minecraft:block/polished_stone_side")
			.setTex("minecraft:block/polished_stone_top",Side.TOP)
			.setTex("minecraft:block/polished_stone_top",Side.BOTTOM)
			.setTex("minecraft:block/polished_stone_top", Side.NORTH)
		);
		dispatcher.addDispatch(new BlockModelHorizontalRotation<>(ModBlocks.BlockMonitor)
			.setAllTextures("minecraft:block/polished_stone_side")
			.setTex("minecraft:block/polished_stone_top",Side.TOP)
			.setTex("minecraft:block/polished_stone_top",Side.BOTTOM)
			.setTex("computers:block/monitor_front", Side.NORTH)
		);
	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {
		dispatcher.addDispatch(new ItemModelStandard(ModItems.WandPercussiveMaintenance)
			.setDisplayPos("firstperson_righthand", HANDHELD_FIRST_PERSON_RIGHT_HAND).setDisplayPos("firstperson_lefthand", HANDHELD_FIRST_PERSON_LEFT_HAND).setDisplayPos("thirdperson_righthand", HANDHELD_THIRD_PERSON_RIGHT_HAND).setDisplayPos("thirdperson_lefthand", HANDHELD_THIRD_PERSON_LEFT_HAND)
			.setIcon("computers:item/wand_of_percussive_maintenance")
		);

	}

	@Override
	public void initEntityModels(EntityRendererDispatcher dispatcher) {

	}


	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}
}
