package hibi.blahaj.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Arm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import hibi.blahaj.block.CuddlyItem;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
        method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Arm;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
        at = @At("TAIL"),
        cancellable = true
    )
    private static void cuddleBlahaj(
        AbstractClientPlayerEntity player,
        Arm arm,
        CallbackInfoReturnable<BipedEntityModel.ArmPose> cir
    ) {
        // CROSSBOW_CHARGE only renders properly in offhand, soooooo
        ItemStack stack = player.getStackInHand(arm == Arm.LEFT ? Hand.OFF_HAND : Hand.MAIN_HAND);
        if (stack.getItem() instanceof CuddlyItem) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            cir.cancel();
        }
    }
}

