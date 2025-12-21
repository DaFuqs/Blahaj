package hibi.blahaj.mixin;

import hibi.blahaj.block.*;
import net.minecraft.client.network.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
	@Inject(
		method = "getArmPose(Lnet/minecraft/entity/PlayerLikeEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
		at = @At("TAIL"),
		cancellable = true
	)
	private static void blahaj$cuddle(PlayerLikeEntity player, ItemStack stack, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
		if (stack.getItem() instanceof CuddlyItem)
			cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
	}
}
