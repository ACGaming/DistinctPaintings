package mod.acgaming.distinctpaintings.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.ForgeHooks;

import mod.acgaming.distinctpaintings.registry.DPRegistry;
import mod.acgaming.distinctpaintings.util.DPUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ForgeHooks.class, remap = false)
public abstract class DPForgeHooksMixin
{
    @Redirect(method = "onPickBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getPickedResult(Lnet/minecraft/util/math/RayTraceResult;)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack dpOnPickBlock(Entity entity, RayTraceResult target)
    {
        if (entity instanceof EntityPainting)
        {
            EntityPainting.EnumArt art = ((EntityPainting) entity).art;
            ItemStack paintingVariant = new ItemStack(DPRegistry.paintingVariant);
            paintingVariant.setTagCompound(DPUtil.createNBTForArt(art));
            return paintingVariant;
        }
        else return target.entityHit.getPickedResult(target);
    }
}
