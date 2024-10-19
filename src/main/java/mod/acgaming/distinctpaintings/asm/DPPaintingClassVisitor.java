package mod.acgaming.distinctpaintings.asm;

import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DPPaintingClassVisitor extends ClassVisitor
{
    public DPPaintingClassVisitor(int api, ClassVisitor classVisitor)
    {
        super(api, classVisitor);
    }

    @Override
    public void visitEnd()
    {
        MethodVisitor mv = cv.visitMethod(
            Opcodes.ACC_PUBLIC,
            "<init>",
            "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/entity/item/EntityPainting$EnumArt;)V",
            null,
            null
        );
        if (mv != null)
        {
            mv.visitCode();
            // Inject constructor code
            mv.visitVarInsn(Opcodes.ALOAD, 0); // load 'this'
            mv.visitVarInsn(Opcodes.ALOAD, 1); // load world parameter
            mv.visitVarInsn(Opcodes.ALOAD, 2); // load BlockPos parameter
            mv.visitVarInsn(Opcodes.ALOAD, 3); // load EnumFacing parameter
            mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "net/minecraft/entity/item/EntityPainting",
                "<init>",
                "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V",
                false
            );
            mv.visitVarInsn(Opcodes.ALOAD, 0); // load 'this'
            mv.visitVarInsn(Opcodes.ALOAD, 4); // load EnumArt parameter
            mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                "net/minecraft/entity/item/EntityPainting",
                FMLLaunchHandler.isDeobfuscatedEnvironment() ? "art" : "field_70522_e",
                "Lnet/minecraft/entity/item/EntityPainting$EnumArt;"
            );
            mv.visitVarInsn(Opcodes.ALOAD, 0); // load 'this'
            mv.visitVarInsn(Opcodes.ALOAD, 3); // load EnumFacing parameter
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "net/minecraft/entity/item/EntityPainting",
                FMLLaunchHandler.isDeobfuscatedEnvironment() ? "updateFacingWithBoundingBox" : "func_174859_a",
                "(Lnet/minecraft/util/EnumFacing;)V",
                false
            );
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(4, 5);
            mv.visitEnd();
        }
        super.visitEnd();
    }
}
