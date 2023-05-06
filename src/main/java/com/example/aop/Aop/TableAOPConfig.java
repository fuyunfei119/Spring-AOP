package com.example.aop.Aop;

import com.example.aop.Annotation.FieldTrigger;
import com.example.aop.Annotation.OnValidate;
import com.example.aop.Annotation.Trigger;
import com.example.aop.Config.TriggerScan;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.core.TypeUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;

@Component
@Aspect
public class TableAOPConfig {

    @Pointcut("execution(* com.example.aop.Record.TestRecord+.Modify(..))")
    public void OnTriggerTableEvent() {}

//    @Before("OnValidate()")
//    public void OnBoforeBeforeValidate(JoinPoint joinPoint) {
//        System.out.println("OnBeforeValidate...............");
//        System.out.println(joinPoint.getSignature());
//    }
//
//    @After("OnValidate()")
//    public void OnAfterAfterValidate() {
//        System.out.println("OnAfterValidate................");
//    }

    @Around("OnTriggerTableEvent()")
    public Object OnDoTest(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println(pjp.getSignature().getDeclaringType());
        for (TypeVariable typeParameter : pjp.getSignature().getDeclaringType().getTypeParameters()) {
            System.out.println(typeParameter);

            System.out.println(Arrays.toString(typeParameter.getBounds()));

            for (Type bound : typeParameter.getBounds()) {
                System.out.println(bound);
                System.out.println(bound.getTypeName());
                System.out.println(TypeUtils.getType(bound.getTypeName()));
            }

//            TypeUtils.getClassName(typeParameter.getBounds())

            System.out.println();
        }



        Object arg = pjp.getArgs()[0];

//        System.out.println(arg.getClass().getName());
//        System.out.println(arg.getClass().getSimpleName());
//        System.out.println(Arrays.toString(arg.getClass().getTypeParameters()));
//        System.out.println(arg.getClass().getTypeName());
//        System.out.println(arg.getClass().getCanonicalName());
//        System.out.println(arg.getClass().getModule());
//        System.out.println(arg.getClass().getPackageName());
//        System.out.println(arg.getClass().getPackage());

        String classpath = arg.getClass().getName().replace("$Fields", "");

        Class<?> aClass = Class.forName(classpath);

        System.out.println(aClass);

        Field field = aClass.getDeclaredField(arg.toString());

        if (field.isAnnotationPresent(OnValidate.class)) {
            OnValidate annotation = field.getAnnotation(OnValidate.class);
            String value = annotation.Value().name();

            System.out.println(value);

            if (!value.isEmpty()) {
                ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TriggerScan.class);

                Collection<Object> beans = applicationContext.getBeansWithAnnotation(FieldTrigger.class).values();

                boolean found = false;

                for (Object bean : beans) {

                    Method[] methods = ReflectionUtils.getDeclaredMethods(bean.getClass());

                    for (Method method : methods) {

                        if (!method.isAnnotationPresent(Trigger.class)) continue;

                        if (method.isAnnotationPresent(Trigger.class) && method.getName().equals(value)) {
                            ReflectionUtils.invokeMethod(method, bean);
                            found = true;
                            break;
                        }
                    }

                    if (found) break;
                }
            }
        }

        Arrays.stream(pjp.getArgs()).forEach(o -> {
//            System.out.println(o);
//            System.out.println(o.getClass().getName());
//
//            String classpath = o.getClass().getName().replace("$Fields", "");
//            System.out.println(classpath);
//            try {
//                Class<?> aClass = Class.forName(classpath);
//                Field field = aClass.getDeclaredField(o.toString());
//                if (field.isAnnotationPresent(OnValidate.class)) {
//                    System.out.println("should be triggered.");
//                }
//            } catch (ClassNotFoundException | NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            }
//
//
//            System.out.println(o.getClass().getSimpleName());
//            System.out.println(o.getClass().getGenericSuperclass().getTypeName());
        });

        Object proceed = pjp.proceed();

        return proceed;
    }
}
