package hibi.blahaj.block;

import hibi.blahaj.*;
import net.minecraft.block.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

import java.util.function.*;

public class CuddlyItem extends BlockItem {

	private final Text tooltip;

	public CuddlyItem(Block block, Settings settings, String tooltip) {
		super(block, settings);
		this.tooltip = tooltip == null ? null : Text.translatable(tooltip).formatted(Formatting.GRAY);
	}

	@Override
	public void onCraftByPlayer(ItemStack stack, PlayerEntity player) {
		super.onCraftByPlayer(stack, player);

		if (player != null) { // compensate for auto-crafter mods that call the wrong method
			stack.set(BlahajDataComponentTypes.OWNER, new OwnerComponent(player.getName()));
		}
	}

	public static final Identifier MINING_SPEED_MODIFIER_ID = Identifier.of(Blahaj.MOD_ID, "base_attack_damage");

	public static AttributeModifiersComponent createAttributeModifiers() {
		return AttributeModifiersComponent.builder()
			.add(EntityAttributes.BLOCK_BREAK_SPEED, new EntityAttributeModifier(MINING_SPEED_MODIFIER_ID, -3.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND)
			.add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, -2.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND)
			.build();
	}


}
