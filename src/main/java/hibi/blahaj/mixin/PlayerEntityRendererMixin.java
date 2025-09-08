package hibi.blahaj.mixin;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import hibi.blahaj.block.CuddlyItem;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
        method = "getArmPose(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
        at = @At("TAIL"),
        cancellable = true
    )
    private static void cuddleBlahaj(
	    PlayerEntity player, ItemStack stack, Hand hand, CallbackInfoReturnable<ArmPose> cir
    ) {
        if (stack.getItem() instanceof CuddlyItem)
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
    }
}

