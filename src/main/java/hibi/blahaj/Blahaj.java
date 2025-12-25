package hibi.blahaj;

import hibi.blahaj.block.*;
import hibi.blahaj.sound.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.loot.v3.*;
import net.fabricmc.fabric.api.object.builder.v1.trade.*;
import net.minecraft.world.entity.npc.villager.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.trading.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;

public class Blahaj implements ModInitializer {

	public static final String MOD_ID = "blahaj";

	public void onInitialize() {
		BlahajDataComponentTypes.register();
		BlahajBlocks.register();
		BlahajSoundEvents.init();
		registerLootTables();
		registerTrades();
	}

	private static void registerLootTables() {
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

	private static void registerTrades() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 5, factories -> {
			factories.add((world, entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 15), new ItemStack(BlahajBlocks.GRAY_SHARK_BLOCK), 2, 30, 0.1f));
		});
	}

}
