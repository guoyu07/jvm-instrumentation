package net.piotrl.jvm.instr;

import net.piotrl.jvm.service.PerformanceAudit;
import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class PerformanceAuditTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        String dotClassName = className.replace('/', '.');
        if (!dotClassName.contains("com.example.jvm")) {
            return null;
        }
        System.out.println("Loaded: " + dotClassName);

        ClassPool cp = ClassPool.getDefault();
        try {
            CtClass ctClazz = cp.get(dotClassName);
            for (CtMethod ctMethod : Arrays.asList(ctClazz.getDeclaredMethods())) {
                Object annotation = ctMethod.getAnnotation(PerformanceAudit.class);
                if (annotation != null) {
                    System.out.println("Performance audit for method: " + ctMethod.getName());
                    wrapWithAudit(ctMethod);
                    return ctClazz.toBytecode();
                }
            }
        } catch (NotFoundException | ClassNotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void wrapWithAudit(CtMethod method) throws CannotCompileException {
        String name = method.getName();

        method.addLocalVariable("elapsedTime", CtClass.longType);

        method.insertBefore("elapsedTime = System.currentTimeMillis();");
        method.insertAfter(" { elapsedTime = System.currentTimeMillis() - elapsedTime; "
                + "System.out.println(\"args: \"+$1+\", \"+$2+\");"
                + "System.out.println(\"\\n [AUDIT][" + name + "] Method elapsedTime = \" + elapsedTime + \"\\n\");}"
        );
    }
}
