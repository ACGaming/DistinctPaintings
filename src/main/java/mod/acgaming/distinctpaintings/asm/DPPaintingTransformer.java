package mod.acgaming.distinctpaintings.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class DPPaintingTransformer implements IClassTransformer
{
    private static final String TARGET_CLASS_NAME = "net.minecraft.entity.item.EntityPainting";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        if (transformedName.equals(TARGET_CLASS_NAME))
        {
            return transformEntityPainting(basicClass);
        }
        return basicClass;
    }

    private byte[] transformEntityPainting(byte[] basicClass)
    {
        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new DPPaintingClassVisitor(Opcodes.ASM5, classWriter);
        classReader.accept(classVisitor, 0);
        return classWriter.toByteArray();
    }
}
