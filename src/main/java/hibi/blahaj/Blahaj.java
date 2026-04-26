package hibi.blahaj;

import hibi.blahaj.block.BlahajBlocks;
import hibi.blahaj.sound.BlahajSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;

public class Blahaj implements ModInitializer {

	public static final String MOD_ID = "blahaj";

	public void onInitialize() {
		BlahajDataComponentTypes.register();
		BlahajBlocks.register();
		BlahajSoundEvents.init();

		LootTableEvents.MODIFY.register((key, builder, lootTableSource, wrapperLookup) -> {
			if (key.equals(BuiltInLootTables.STRONGHOLD_CROSSING) || key.equals(BuiltInLootTables.STRONGHOLD_CORRIDOR)) {
				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.GRAY_SHARK_BLOCK).setWeight(5))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(100));
				builder.withPool(pb);
			} else if (key.equals(BuiltInLootTables.VILLAGE_PLAINS_HOUSE)) {
				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.GRAY_SHARK_BLOCK))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(43));
				builder.withPool(pb);
			} else if (key.equals(BuiltInLootTables.VILLAGE_TAIGA_HOUSE) || key.equals(BuiltInLootTables.VILLAGE_SNOWY_HOUSE)) {
				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.GRAY_SHARK_BLOCK).setWeight(5))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(54));
				builder.withPool(pb);
			} else if (key.equals(BuiltInLootTables.FLETCHER_GIFT)
				|| key.equals(BuiltInLootTables.BUTCHER_GIFT)
				|| key.equals(BuiltInLootTables.LEATHERWORKER_GIFT)) {

				LootPool.Builder pb = LootPool.lootPool()
					.add(LootItem.lootTableItem(BlahajBlocks.BROWN_BEAR_BLOCK).setWeight(5))
					.add(LootItem.lootTableItem(Items.AIR).setWeight(25));
				builder.withPool(pb);
			}
		});
	}

}
