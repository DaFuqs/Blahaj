package hibi.blahaj;

import hibi.blahaj.block.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;

@Mod(value = Blahaj.MOD_ID, dist = Dist.CLIENT)
public class BlahajClient {

	public BlahajClient(IEventBus modBus) {
		BlahajBlocks.registerClient();
	}

}
