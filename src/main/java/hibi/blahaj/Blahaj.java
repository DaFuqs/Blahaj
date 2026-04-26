package hibi.blahaj;

import hibi.blahaj.block.BlahajBlocks;
import hibi.blahaj.sound.BlahajSoundEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(Blahaj.MOD_ID)
public class Blahaj {

	public static final String MOD_ID = "blahaj";

	public Blahaj(IEventBus modBus) {
		BlahajDataComponentTypes.register(modBus);
		BlahajBlocks.register(modBus);
		BlahajSoundEvents.register(modBus);

		modBus.addListener(Blahaj::buildCreativeModeTabContents);
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
