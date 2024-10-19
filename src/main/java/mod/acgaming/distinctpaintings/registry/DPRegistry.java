package mod.acgaming.distinctpaintings.registry;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import mod.acgaming.distinctpaintings.DistinctPaintings;
import mod.acgaming.distinctpaintings.item.DPItemPaintingVariant;

@GameRegistry.ObjectHolder(DistinctPaintings.MOD_ID)
public class DPRegistry
{
    @GameRegistry.ObjectHolder("painting_variant")
    public static Item paintingVariant;

    public static void registerItems()
    {
        paintingVariant = new DPItemPaintingVariant(EntityPainting.class);
        ForgeRegistries.ITEMS.register(paintingVariant);
    }
}
