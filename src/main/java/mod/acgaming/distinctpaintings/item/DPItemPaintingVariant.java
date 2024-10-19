package mod.acgaming.distinctpaintings.item;

import java.lang.reflect.Constructor;
import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHangingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.distinctpaintings.DistinctPaintings;

public class DPItemPaintingVariant extends ItemHangingEntity
{
    public DPItemPaintingVariant(Class<? extends EntityPainting> entityClass)
    {
        super(entityClass);
        this.setRegistryName(DistinctPaintings.MOD_ID, "painting_variant");
        this.setTranslationKey("painting.name");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        String title = getTitleFromStack(stack);
        return I18n.format(this.getTranslationKey()) + " (" + title + ")";
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
    {
        if (isInCreativeTab(tab))
        {
            for (EntityPainting.EnumArt art : EntityPainting.EnumArt.values())
            {
                ItemStack itemStack = new ItemStack(this);
                itemStack.setTagCompound(createNBTForArt(art));
                items.add(itemStack);
            }
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = player.getHeldItem(hand);
        BlockPos blockPos = pos.offset(facing);

        if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && player.canPlayerEdit(blockPos, facing, stack))
        {
            String motive = getMotiveFromStack(stack);
            EntityPainting.EnumArt art = EntityPainting.EnumArt.valueOf(motive);

            try
            {
                Class<?> entityPaintingClass = EntityPainting.class;
                Constructor<?> constructor = entityPaintingClass.getConstructor(World.class, BlockPos.class, EnumFacing.class, EntityPainting.EnumArt.class);

                EntityPainting painting = (EntityPainting) constructor.newInstance(world, blockPos, facing, art);

                if (painting.onValidSurface())
                {
                    if (!world.isRemote)
                    {
                        painting.playPlaceSound();
                        world.spawnEntity(painting);
                    }

                    stack.shrink(1);
                }

                return EnumActionResult.SUCCESS;
            }
            catch (NoSuchMethodException e)
            {
                DistinctPaintings.LOGGER.fatal("Custom EntityPainting constructor with EnumArt is not available!");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return EnumActionResult.FAIL;
    }

    private NBTTagCompound createNBTForArt(EntityPainting.EnumArt art)
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("Motive", art.name());
        compound.setString("Title", art.title);
        return compound;
    }

    private String getMotiveFromStack(ItemStack stack)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Motive"))
        {
            return stack.getTagCompound().getString("Motive");
        }
        return "UNKNOWN";
    }

    private String getTitleFromStack(ItemStack stack)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Title"))
        {
            return stack.getTagCompound().getString("Title");
        }
        return "UNKNOWN";
    }
}