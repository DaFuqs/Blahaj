package hibi.blahaj;

import hibi.blahaj.block.*;
import hibi.blahaj.sound.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.npc.villager.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.*;
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
		NeoForge.EVENT_BUS.addListener(Blahaj::lootTableLoad);
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

	@SubscribeEvent // on the mod event bus
	public static void lootTableLoad(LootTableLoadEvent event) {
		ResourceKey<LootTable> key = event.getKey();

		if (key.equals(BuiltInLootTables.STRONGHOLD_CROSSING) || key.equals(BuiltInLootTables.STRONGHOLD_CORRIDOR)) {
			addLootPoolWithChance(event, BlahajBlocks.GRAY_SHARK_BLOCK, 0.05F);
		} else if (key.equals(BuiltInLootTables.VILLAGE_PLAINS_HOUSE)) {
			addLootPoolWithChance(event, BlahajBlocks.GRAY_SHARK_BLOCK, 0.02F);
		} else if (key.equals(BuiltInLootTables.VILLAGE_TAIGA_HOUSE) || key.equals(BuiltInLootTables.VILLAGE_SNOWY_HOUSE)) {
			addLootPoolWithChance(event, BlahajBlocks.GRAY_SHARK_BLOCK, 0.1F);
		} else if (key.equals(BuiltInLootTables.FLETCHER_GIFT) || key.equals(BuiltInLootTables.BUTCHER_GIFT) || key.equals(BuiltInLootTables.LEATHERWORKER_GIFT)) {
			addLootPoolWithChance(event, BlahajBlocks.BROWN_BEAR_BLOCK, 0.1F);
		}
	}

	private static void addLootPoolWithChance(LootTableLoadEvent event, DeferredBlock<Block> block, float chance) {
		LootPool.Builder pb = LootPool.lootPool().add(LootItem.lootTableItem(block));
		pb.when(() -> new LootItemRandomChanceCondition(ConstantValue.exactly(chance)));
		event.getTable().addPool(pb.build());
	}

}
