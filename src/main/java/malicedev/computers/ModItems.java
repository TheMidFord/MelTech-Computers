package malicedev.computers;

import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;

import static malicedev.computers.Main.MOD_ID;
import static malicedev.computers.Main.itemId;

public class ModItems {
	private ModItems() {}
	public static Item WandPercussiveMaintenance;

	public static void init() {
		WandPercussiveMaintenance = new ItemBuilder(MOD_ID)
			.setStackSize(1)
			.build(new Item("wand_of_percussive_maintenance", "meltech-computers:item/wand_percussive_maintenance", itemId++));
	}
}
