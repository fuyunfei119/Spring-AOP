package com.example.aop.Annotation;

import com.example.aop.System.DeleteTriggerType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OnDelete {

    DeleteTriggerType value();
}
