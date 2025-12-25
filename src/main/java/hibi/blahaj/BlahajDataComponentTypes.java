package hibi.blahaj;

import net.minecraft.core.component.*;
import net.minecraft.core.registries.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

public class BlahajDataComponentTypes {

	public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Blahaj.MOD_ID);

	public static final Supplier<DataComponentType<OwnerComponent>> OWNER = REGISTRAR.registerComponentType(
		"owner",
		builder -> builder
			// The codec to read/write the data to disk
			.persistent(OwnerComponent.CODEC)
			// The codec to read/write the data across the network
			.networkSynchronized(OwnerComponent.PACKET_CODEC)
			.cacheEncoding()
	);

	public static void register(IEventBus eventBus) {
		REGISTRAR.register(eventBus);
	}

}
