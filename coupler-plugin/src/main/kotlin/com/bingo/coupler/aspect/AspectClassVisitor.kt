package com.bingo.coupler.aspect

import groovyjarjarasm.asm.Opcodes.ACC_ABSTRACT
import groovyjarjarasm.asm.Opcodes.ACC_NATIVE
import javassist.CtMethod
import org.objectweb.asm.*

class AspectClassVisitor(
    val pointClassReader: ClassReader,
    val pointCtMethod: CtMethod,
    classVisitor: ClassVisitor
) :
    ClassVisitor(Opcodes.ASM9, classVisitor) {


    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null && !"<init>".equals(name) && !"<clinit>".equals(name)) {
            val isAbstractMethod = (access and ACC_ABSTRACT) != 0;
            val isNativeMethod = (access and ACC_NATIVE) != 0;
            if (!isAbstractMethod && !isNativeMethod) {
                if (name == pointCtMethod.name && descriptor == pointCtMethod.methodInfo.descriptor) {
                    mv = MyMethodVisitor(api, pointClassReader, pointCtMethod);
                }
            }
        }

        return mv
    }

    private class MyMethodVisitor(
        api: Int,
        val pointClassReader: ClassReader,
        val pointCtMethod: CtMethod
    ) : MethodVisitor(api) {

        override fun visitMethodInsn(
            opcode: Int,
            owner: String?,
            name: String?,
            descriptor: String?,
            isInterface: Boolean
        ) {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        }

        override fun visitCode() {
            super.visitCode()



        }


    }

}