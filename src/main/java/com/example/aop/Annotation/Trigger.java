package com.example.aop.Annotation;

import com.example.aop.System.SystemTriggerType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Trigger {

     String MethodName() default "";

     SystemTriggerType RaisedFor();
}
