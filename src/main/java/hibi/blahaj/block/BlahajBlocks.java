package hibi.blahaj.block;

import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.chunk.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;

import static hibi.blahaj.Blahaj.*;

public class BlahajBlocks {

	private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

	public static List<DeferredBlock<Block>> BLOCK_LIST = new ArrayList<>();
	public static List<DeferredItem<Item>> ITEM_LIST = new ArrayList<>();

	public static final Identifier GRAY_SHARK_ID = Identifier.fromNamespaceAndPath(MOD_ID, "gray_shark");
	public static final Identifier BLAHAJ_ID = Identifier.fromNamespaceAndPath(MOD_ID, "blue_shark");
	public static final Identifier BLAVINGAD_ID = Identifier.fromNamespaceAndPath(MOD_ID, "blue_whale");
	public static final Identifier BREAD_ID = Identifier.fromNamespaceAndPath(MOD_ID, "bread");
	public static final Identifier BROWN_BEAR_ID = Identifier.fromNamespaceAndPath(MOD_ID, "brown_bear");

	public static final DeferredBlock<Block> GRAY_SHARK_BLOCK = registerCuddlyBlockAndItem(GRAY_SHARK_ID, "block.blahaj.gray_shark.tooltip");
	public static final DeferredBlock<Block> BLAHAJ_BLOCK = registerCuddlyBlockAndItem(BLAHAJ_ID, "block.blahaj.blue_shark.tooltip");
	public static final DeferredBlock<Block> BLAVINGAD_BLOCK = registerCuddlyBlockAndItem(BLAVINGAD_ID, "block.blahaj.blue_whale.tooltip");
	public static final DeferredBlock<Block> BREAD_BLOCK = registerCuddlyBlockAndItem(BREAD_ID, null);
	public static final DeferredBlock<Block> BROWN_BEAR_BLOCK = registerCuddlyBlockAndItem(BROWN_BEAR_ID, "block.blahaj.brown_bear.tooltip");

	public static final List<String> PRIDE_NAMES = List.of(
		"ace", "agender", "aro", "aroace", "bi", "demiboy", "demigirl",
		"demi_r", "demi_s", "enby", "gay", "genderfluid", "genderqueer", "greyrose",
		"grey_r", "grey_s", "intersex", "lesbian", "pan", "poly", "pride", "trans");


	static {
		for (String name : PRIDE_NAMES) {
			Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, name + "_shark");
			registerCuddlyBlockAndItem(id, "block.blahaj.blue_shark.tooltip");
		}
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
	}

	public static DeferredBlock<Block> registerCuddlyBlockAndItem(Identifier id, String tooltip) {
		DeferredBlock<Block> registeredBlock = BLOCKS.register(id.getPath(), identifier -> new CuddlyBlock(
				BlockBehaviour.Properties
					.ofFullCopy(Blocks.WHITE_WOOL)
					.setId(ResourceKey.create(Registries.BLOCK, identifier))
			)
		);

		DeferredItem<Item> registeredItem = ITEMS.register(id.getPath(), identifier -> new CuddlyItem(registeredBlock.get(), new Item.Properties()
			.setId(ResourceKey.create(Registries.ITEM, identifier))
			.useBlockDescriptionPrefix()
			.stacksTo(1)
			.attributes(CuddlyItem.createAttributeModifiers())
			.equippableUnswappable(EquipmentSlot.HEAD), tooltip));

		BLOCK_LIST.add(registeredBlock);
		ITEM_LIST.add(registeredItem);

		return registeredBlock;
	}

	public static void registerClient() {
		for (DeferredBlock<Block> block : BLOCK_LIST) {
			ItemBlockRenderTypes.setRenderLayer(block.get(), ChunkSectionLayer.CUTOUT);
		}
	}

}
