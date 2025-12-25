package hibi.blahaj.block;

import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.client.renderer.chunk.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;

import static hibi.blahaj.Blahaj.*;

public class BlahajBlocks {

	public static final Identifier GRAY_SHARK_ID = Identifier.fromNamespaceAndPath(MOD_ID, "gray_shark");
	public static final Identifier BLAHAJ_ID = Identifier.fromNamespaceAndPath(MOD_ID, "blue_shark");
	public static final Identifier BLAVINGAD_ID = Identifier.fromNamespaceAndPath(MOD_ID, "blue_whale");
	public static final Identifier BREAD_ID = Identifier.fromNamespaceAndPath(MOD_ID, "bread");
	public static final Identifier BROWN_BEAR_ID = Identifier.fromNamespaceAndPath(MOD_ID, "brown_bear");

	public static Block GRAY_SHARK_BLOCK;
	public static Block BLAHAJ_BLOCK;
	public static Block BLAVINGAD_BLOCK;
	public static Block BREAD_BLOCK;
	public static Block BROWN_BEAR_BLOCK;

	public static final List<String> PRIDE_NAMES = List.of(
		"ace", "agender", "aro", "aroace", "bi", "demiboy", "demigirl",
		"demi_r", "demi_s", "enby", "gay", "genderfluid", "genderqueer", "greyrose",
		"grey_r", "grey_s", "intersex", "lesbian", "pan", "poly", "pride", "trans");

	public static List<Block> BLOCKS = new ArrayList<>();
	public static List<Item> ITEMS = new ArrayList<>();

	public static void register() {
		GRAY_SHARK_BLOCK = registerCuddlyBlockAndItem(GRAY_SHARK_ID, "block.blahaj.gray_shark.tooltip");
		BLAHAJ_BLOCK = registerCuddlyBlockAndItem(BLAHAJ_ID, "block.blahaj.blue_shark.tooltip");
		BLAVINGAD_BLOCK = registerCuddlyBlockAndItem(BLAVINGAD_ID, "block.blahaj.blue_whale.tooltip");
		BREAD_BLOCK = registerCuddlyBlockAndItem(BREAD_ID, null);
		BROWN_BEAR_BLOCK = registerCuddlyBlockAndItem(BROWN_BEAR_ID, "block.blahaj.brown_bear.tooltip");

		for (String name : PRIDE_NAMES) {
			Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, name + "_shark");
			registerCuddlyBlockAndItem(id, "block.blahaj.blue_shark.tooltip");
		}

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			for (Item item : ITEMS) {
				entries.accept(new ItemStack(item));
			}
		});
	}

	public static Block registerCuddlyBlockAndItem(Identifier id, String tooltip) {
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
		Block block = Registry.register(BuiltInRegistries.BLOCK, id, new CuddlyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).setId(blockKey)));
		Item item = Registry.register(BuiltInRegistries.ITEM, id, new CuddlyItem(block, new net.minecraft.world.item.Item.Properties()
			.setId(itemKey)
			.useBlockDescriptionPrefix()
			.stacksTo(1)
			.attributes(CuddlyItem.createAttributeModifiers())
			.equippableUnswappable(EquipmentSlot.HEAD), tooltip));

		BLOCKS.add(block);
		ITEMS.add(item);

		return block;
	}

	public static void registerClient() {
		for (Block block : BLOCKS) {
			BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.CUTOUT);
		}
	}

}
