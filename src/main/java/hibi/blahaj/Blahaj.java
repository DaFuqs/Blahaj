package hibi.blahaj;

import hibi.blahaj.block.*;
import hibi.blahaj.sound.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.loot.v3.*;
import net.fabricmc.fabric.api.object.builder.v1.trade.*;
import net.minecraft.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.entry.*;
import net.minecraft.village.*;

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
			if (key.equals(LootTables.STRONGHOLD_CROSSING_CHEST) || key.equals(LootTables.STRONGHOLD_CORRIDOR_CHEST)) {
				LootPool.Builder pb = LootPool.builder()
					.with(ItemEntry.builder(BlahajBlocks.GRAY_SHARK_BLOCK).weight(5))
					.with(ItemEntry.builder(Items.AIR).weight(100));
				builder.pool(pb);
			} else if (key.equals(LootTables.VILLAGE_PLAINS_CHEST)) {
				LootPool.Builder pb = LootPool.builder()
					.with(ItemEntry.builder(BlahajBlocks.GRAY_SHARK_BLOCK))
					.with(ItemEntry.builder(Items.AIR).weight(43));
				builder.pool(pb);
			} else if (key.equals(LootTables.VILLAGE_TAIGA_HOUSE_CHEST) || key.equals(LootTables.VILLAGE_SNOWY_HOUSE_CHEST)) {
				LootPool.Builder pb = LootPool.builder()
					.with(ItemEntry.builder(BlahajBlocks.GRAY_SHARK_BLOCK).weight(5))
					.with(ItemEntry.builder(Items.AIR).weight(54));
				builder.pool(pb);
			} else if (key.equals(LootTables.HERO_OF_THE_VILLAGE_FLETCHER_GIFT_GAMEPLAY)
				|| key.equals(LootTables.HERO_OF_THE_VILLAGE_BUTCHER_GIFT_GAMEPLAY)
				|| key.equals(LootTables.HERO_OF_THE_VILLAGE_LEATHERWORKER_GIFT_GAMEPLAY)) {

				LootPool.Builder pb = LootPool.builder()
					.with(ItemEntry.builder(BlahajBlocks.BROWN_BEAR_BLOCK).weight(5))
					.with(ItemEntry.builder(Items.AIR).weight(25));
				builder.pool(pb);
			}
		});
	}

	private static void registerTrades() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 5, factories -> {
			factories.add((world, entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 15), new ItemStack(BlahajBlocks.GRAY_SHARK_BLOCK), 2, 30, 0.1f));
		});
	}

}
