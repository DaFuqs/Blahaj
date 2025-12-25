package hibi.blahaj.sound;

import hibi.blahaj.*;
import net.minecraft.core.registries.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;

public class BlahajSoundEvents {

	private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Blahaj.MOD_ID);

	public static final List<SoundEvent> BLOCK_CUDDLY_ITEM = new ArrayList<>();
    public static final SoundEvent BLOCK_CUDDLY_ITEM_HIT = register("block.blahaj.cuddly_item.hit");

	private static SoundEvent register(String name) {
		SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(Blahaj.id(name));
		SOUND_EVENTS.register(name, () -> soundEvent);
		return soundEvent;
	}

	static {
		for (int i = 1; i < 6; i++) {
			BLOCK_CUDDLY_ITEM.add(register("block.blahaj.cuddly_item.use." + i));
		}
	}

	public static SoundEvent getRandomSqueak(RandomSource random) {
		return BLOCK_CUDDLY_ITEM.get(random.nextInt(BLOCK_CUDDLY_ITEM.size()));
	}

	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}

}
