package hibi.blahaj;

import com.mojang.serialization.*;
import org.jspecify.annotations.*;

import java.util.function.*;

public class OwnerComponent implements TooltipProvider {

	public static final Codec<OwnerComponent> CODEC = ComponentSerialization.CODEC.xmap(OwnerComponent::new, OwnerComponent::getOwnerName);
	public static final StreamCodec<RegistryFriendlyByteBuf, OwnerComponent> PACKET_CODEC = ComponentSerialization.STREAM_CODEC.map(OwnerComponent::new, OwnerComponent::getOwnerName);

	final Component ownerName;

	public OwnerComponent(Component ownerName) {
		this.ownerName = ownerName;
	}

	private Component getOwnerName() {
		return this.ownerName;
	}

	@Override
	public void addToTooltip(Item.@NonNull TooltipContext context, @NonNull Consumer<Component> textConsumer, @NonNull TooltipFlag type, DataComponentGetter components) {
		@Nullable OwnerComponent owner = components.get(BlahajDataComponentTypes.OWNER);
		if (owner != null) {
			@Nullable Component customName = components.get(DataComponents.CUSTOM_NAME);
			if (customName == null) {
				textConsumer.accept(Component.translatable("tooltip.blahaj.owner.craft", owner.getOwnerName()).withStyle(ChatFormatting.GRAY));
			} else {
				textConsumer.accept(Component.translatable("tooltip.blahaj.owner.rename", customName, owner.getOwnerName()).withStyle(ChatFormatting.GRAY));
			}
		}
	}

}
