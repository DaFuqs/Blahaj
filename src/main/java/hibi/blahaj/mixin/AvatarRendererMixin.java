package hibi.blahaj.mixin;

import hibi.blahaj.block.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(AvatarRenderer.class)
public class AvatarRendererMixin {
	@Inject(
		method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
		at = @At("TAIL"),
		cancellable = true
	)
	private static void blahaj$cuddle(Avatar player, ItemStack stack, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
		if (stack.getItem() instanceof CuddlyItem)
			cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
	}
}
