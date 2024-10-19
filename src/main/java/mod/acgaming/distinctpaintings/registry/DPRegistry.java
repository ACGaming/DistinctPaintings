package mod.acgaming.distinctpaintings.registry;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import mod.acgaming.distinctpaintings.DistinctPaintings;
import mod.acgaming.distinctpaintings.item.DPItemPaintingVariant;

@Mod.EventBusSubscriber(modid = DistinctPaintings.MOD_ID)
@GameRegistry.ObjectHolder(DistinctPaintings.MOD_ID)
public class DPRegistry
{
    @GameRegistry.ObjectHolder("painting_variant")
    public static DPItemPaintingVariant paintingVariant;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        paintingVariant = new DPItemPaintingVariant(EntityPainting.class);
        event.getRegistry().register(paintingVariant);
        DistinctPaintings.LOGGER.info("Painting variants registered");
    }

    public static void updatePaintingVariants()
    {
        paintingVariant = new DPItemPaintingVariant(EntityPainting.class);
        DistinctPaintings.LOGGER.info("Painting variants updated");
    }
}
