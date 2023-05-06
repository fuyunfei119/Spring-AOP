package com.example.aop.Annotation;

import com.example.aop.System.ModifyTriggerType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OnModify {

    ModifyTriggerType value();
}
