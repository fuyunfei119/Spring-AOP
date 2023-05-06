package com.example.aop.Annotation;

import com.example.aop.System.InsertTriggerType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OnInsert {

    InsertTriggerType value();
}
