package mod.acgaming.distinctpaintings.mixin;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import mod.acgaming.distinctpaintings.registry.DPRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPainting.class)
public abstract class DPEntityPaintingMixin extends EntityHanging
{
    protected DPEntityPaintingMixin(World world)
    {
        super(world);
    }

    @Redirect(method = "onBroken", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/EntityPainting;entityDropItem(Lnet/minecraft/item/ItemStack;F)Lnet/minecraft/entity/item/EntityItem;"))
    private EntityItem dpOnBroken(EntityPainting entityPainting, ItemStack stack, float offsetY)
    {
        ItemStack paintingVariant = new ItemStack(DPRegistry.paintingVariant);
        paintingVariant.setTagCompound(dp$createNBTForArt(entityPainting.art));
        return this.entityDropItem(paintingVariant, offsetY);
    }

    @Unique
    private NBTTagCompound dp$createNBTForArt(EntityPainting.EnumArt art)
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("Motive", art.name());
        compound.setString("Title", art.title);
        return compound;
    }
}
