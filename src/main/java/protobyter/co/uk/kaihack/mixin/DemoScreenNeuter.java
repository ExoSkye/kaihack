package protobyter.co.uk.kaihack.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.DemoScreen.class)
public abstract class DemoScreenNeuter {
    @Inject(method = "init()V", at = @At("HEAD"), cancellable = true)
    private void cancelDemoScreen(CallbackInfo ci) {
        ci.cancel();
    }
}
