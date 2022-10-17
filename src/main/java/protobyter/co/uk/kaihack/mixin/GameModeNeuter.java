package protobyter.co.uk.kaihack.mixin;

import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.network.ClientPlayerInteractionManager.class)
public abstract class GameModeNeuter {
    @Shadow private GameMode gameMode;

    @Inject(method = "setGameMode(Lnet/minecraft/world/GameMode;)V", at = @At("HEAD"), cancellable = true)
    private void gameMode(GameMode _gameMode, CallbackInfo ci) {
        gameMode = GameMode.SURVIVAL;
        ci.cancel();
    }
}
