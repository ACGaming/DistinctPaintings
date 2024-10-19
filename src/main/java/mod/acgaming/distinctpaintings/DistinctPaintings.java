package mod.acgaming.distinctpaintings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import mod.acgaming.distinctpaintings.registry.DPRegistry;

@Mod(modid = DistinctPaintings.MOD_ID, name = DistinctPaintings.NAME, version = DistinctPaintings.VERSION, acceptedMinecraftVersions = DistinctPaintings.ACCEPTED_VERSIONS, dependencies = DistinctPaintings.DEPENDENCIES)
public class DistinctPaintings
{
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = Tags.NAME;
    public static final String VERSION = Tags.VERSION;
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String DEPENDENCIES = "after:jsonpaintings";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        DPRegistry.registerItems();
        LOGGER.info("Distinct Paintings initialized");
    }
}
