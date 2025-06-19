package hibi.blahaj;

import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.component.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

import java.util.function.*;

public class BlahajDataComponentTypes {

	public static final ComponentType<OwnerComponent> OWNER = register("owner", (builder) -> builder.codec(OwnerComponent.CODEC).packetCodec(OwnerComponent.PACKET_CODEC).cache());

	private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Blahaj.MOD_ID, id), builderOperator.apply(ComponentType.builder()).build());
	}

	public static void register() {
        ComponentTooltipAppenderRegistry.addFirst(OWNER);
	}

}
