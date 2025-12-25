package hibi.blahaj;

import hibi.blahaj.block.*;
import hibi.blahaj.sound.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.npc.villager.*;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.*;
import net.neoforged.neoforge.event.village.*;
import net.neoforged.neoforge.registries.*;

@Mod(Blahaj.MOD_ID)
public class Blahaj {

	public static final String MOD_ID = "blahaj";

	public Blahaj(IEventBus modBus) {
		BlahajDataComponentTypes.register(modBus);
		BlahajBlocks.register(modBus);
		BlahajSoundEvents.register(modBus);

		modBus.addListener(Blahaj::buildCreativeModeTabContents);
		NeoForge.EVENT_BUS.addListener(Blahaj::tradeWithVillager);
	}

	public static Identifier id(String id) {
		return Identifier.fromNamespaceAndPath(MOD_ID, id);
	}

	@SubscribeEvent
	public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			for (DeferredHolder<Item, ? extends Item> item : BlahajBlocks.ITEMS.getEntries()) {
				event.accept(item.get());
			}
		}
	}

	@SubscribeEvent
	public static void tradeWithVillager(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.SHEPHERD) {
			event.getTrades().get(5).add(new VillagerTrades.ItemsForEmeralds(new ItemStack(BlahajBlocks.GRAY_SHARK_BLOCK.get().asItem()), 15, 1, 2, 30, 0.1f));
		}
	}

	/*
	@SubscribeEvent // on the mod event bus
	public static void lootTableLoad(LootTableLoadEvent event) {
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
	}*/

}
