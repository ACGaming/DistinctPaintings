package mod.acgaming.distinctpaintings.util;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DPUtil
{
    public static NBTTagCompound createNBTForArt(EntityPainting.EnumArt art)
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("Motive", art.name());
        compound.setString("Title", art.title);
        return compound;
    }

    public static String getMotiveFromStack(ItemStack stack)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Motive"))
        {
            return stack.getTagCompound().getString("Motive");
        }
        return "UNKNOWN";
    }

    public static String getTitleFromStack(ItemStack stack)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Title"))
        {
            return stack.getTagCompound().getString("Title");
        }
        return "UNKNOWN";
    }
}
