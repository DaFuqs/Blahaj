package hibi.blahaj.block;

import hibi.blahaj.*;
import net.minecraft.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.block.*;
import org.jspecify.annotations.*;

import java.util.function.*;

public class CuddlyItem extends BlockItem {

	private final Component tooltip;

	public CuddlyItem(Block block, Properties settings, String tooltip) {
		super(block, settings);
		this.tooltip = tooltip == null ? null : Component.translatable(tooltip).withStyle(ChatFormatting.GRAY);
	}

	@Override
	public void onCraftedBy(@NonNull ItemStack stack, @NonNull Player player) {
		super.onCraftedBy(stack, player);

		if (player != null) { // compensate for auto-crafter mods that call the wrong method
			stack.set(BlahajDataComponentTypes.OWNER, new OwnerComponent(player.getName()));
		}
	}

	@Override
	public void appendHoverText(@NonNull ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay displayComponent, @NonNull Consumer<Component> textConsumer, @NonNull TooltipFlag type) {
		super.appendHoverText(stack, context, displayComponent, textConsumer, type);

		if (this.tooltip != null) {
			textConsumer.accept(this.tooltip);
		}

		// this is kinda dum, but I don't really feel like mixin in there
		// and I haven't found a FAPI event for that exact injection point
		stack.addToTooltip(BlahajDataComponentTypes.OWNER, context, displayComponent, textConsumer, type);
	}

	public static final Identifier MINING_SPEED_MODIFIER_ID = Identifier.fromNamespaceAndPath(Blahaj.MOD_ID, "base_attack_damage");

	public static ItemAttributeModifiers createAttributeModifiers() {
		return ItemAttributeModifiers.builder()
			.add(Attributes.BLOCK_BREAK_SPEED, new AttributeModifier(MINING_SPEED_MODIFIER_ID, -3.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, -2.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), EquipmentSlotGroup.MAINHAND)
			.build();
	}


}
