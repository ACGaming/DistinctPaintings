package mod.acgaming.distinctpaintings.core;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import zone.rong.mixinbooter.IEarlyMixinLoader;

@IFMLLoadingPlugin.Name("DistinctPaintingsCoreMod")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class DistinctPaintingsCoreMod implements IFMLLoadingPlugin, IEarlyMixinLoader
{
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] {"mod.acgaming.distinctpaintings.asm.DPPaintingTransformer"};
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public List<String> getMixinConfigs()
    {
        return Collections.singletonList("mixins.distinctpaintings.json");
    }
}
