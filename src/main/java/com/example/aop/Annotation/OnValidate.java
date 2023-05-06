package com.example.aop.Annotation;

import com.example.aop.System.ValidateTriggerType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OnValidate {

    ValidateTriggerType Value();
}
