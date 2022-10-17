package protobyter.co.uk.kaihack.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.server.network.DemoServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DemoServerPlayerInteractionManager.class)
public abstract class DemoNeuter {
    @Shadow private boolean demoEnded;

    @Inject(method = "update()V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;update()V"), cancellable = true)
    private void update(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "sendDemoReminder()V", at = @At("HEAD"), cancellable = true)
    private void sendDemoReminderInject(CallbackInfo ci) {
        this.demoEnded = false;
        ci.cancel();
    }

    @Inject(
            method = "Lnet/minecraft/server/network/DemoServerPlayerInteractionManager;processBlockBreakingAction(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/network/packet/c2s/play/PlayerActionC2SPacket$Action;Lnet/minecraft/util/math/Direction;II)V",
            at = @At("HEAD")
    )
    private void forceProcessBlockBreakingAction(BlockPos pos, PlayerActionC2SPacket.Action action, Direction direction, int worldHeight, int sequence, CallbackInfo ci) {
        demoEnded = false;
    }

    @Inject(
            method = "Lnet/minecraft/server/network/DemoServerPlayerInteractionManager;interactItem(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD")
    )
    private void forceInteractItem(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        demoEnded = false;
    }

    @Inject(
            method = "Lnet/minecraft/server/network/DemoServerPlayerInteractionManager;interactBlock(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD")
    )
    private void forceInteractBlock(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        demoEnded = false;
    }
}
