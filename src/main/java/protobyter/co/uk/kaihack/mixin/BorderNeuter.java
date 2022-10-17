package protobyter.co.uk.kaihack.mixin;

import net.minecraft.network.listener.ClientPlayPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket.class)
public class BorderNeuter {
    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at=@At("HEAD"), cancellable = true)
    private void cancelApply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci) {
        ci.cancel();
    }
}
