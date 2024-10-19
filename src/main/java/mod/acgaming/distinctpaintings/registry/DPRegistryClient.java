package mod.acgaming.distinctpaintings.registry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import mod.acgaming.distinctpaintings.DistinctPaintings;

@Mod.EventBusSubscriber(modid = DistinctPaintings.MOD_ID, value = Side.CLIENT)
public class DPRegistryClient
{
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(DPRegistry.paintingVariant, 0, new ModelResourceLocation("minecraft:painting", "inventory"));
    }
}
