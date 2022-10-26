package protobyter.co.uk.kaihack.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import com.mojang.logging.LogUtils;

@Mixin(net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.class)
public abstract class ServerNeuter {
    static private final int PRECISION = 100;

    private static float removePrecisionFloat(float value) {
        return (float)removePrecisionDouble(value);
    }

    private static double removePrecisionDouble(double value) {
        return (double) (long)(value * PRECISION) / PRECISION;
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static double modifyX(double value)
    {
        double retval = removePrecisionDouble(value);

        if (((long)retval * 1000) % 10 != 0) {
            LogUtils.getLogger().warn("X was imprecise");
        }

        return retval;
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 2, argsOnly = true)
    private static double modifyZ(double value)
    {
        double retval = removePrecisionDouble(value);

        if (((long)retval * 1000) % 10 != 0) {
            LogUtils.getLogger().warn("Z was imprecise");
        }

        return retval;
    }

    @ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static float modifyYaw(float value)
    {

}
