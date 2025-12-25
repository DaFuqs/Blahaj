package hibi.blahaj.sound;

import hibi.blahaj.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;

import java.util.*;

public class BlahajSoundEvents {

    public static final List<SoundEvent> BLOCK_CUDDLY_ITEM = new ArrayList<>();
    public static final SoundEvent BLOCK_CUDDLY_ITEM_HIT = register("block.blahaj.cuddly_item.hit");

    private static Identifier id(String id) {
		return Identifier.fromNamespaceAndPath(Blahaj.MOD_ID, id);
    }

    private static SoundEvent register(String id) {
		return Registry.register(BuiltInRegistries.SOUND_EVENT, id(id), SoundEvent.createVariableRangeEvent(id(id)));
    }

    public static void init() {
		for (int i = 1; i < 6; i++) {
			BLOCK_CUDDLY_ITEM.add(register("block.blahaj.cuddly_item.use." + i));
		}
	}

	public static SoundEvent getRandomSqueak(RandomSource random) {
		return BLOCK_CUDDLY_ITEM.get(random.nextInt(BLOCK_CUDDLY_ITEM.size()));
	}

}
