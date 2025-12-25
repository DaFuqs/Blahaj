package hibi.blahaj;

import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;

import java.util.function.*;

public class BlahajDataComponentTypes {

	public static final DataComponentType<OwnerComponent> OWNER = register("owner", (builder) -> builder.persistent(OwnerComponent.CODEC).networkSynchronized(OwnerComponent.PACKET_CODEC).cacheEncoding());

	private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
		return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(Blahaj.MOD_ID, id), builderOperator.apply(DataComponentType.builder()).build());
	}

	public static void register() {

	}

}
