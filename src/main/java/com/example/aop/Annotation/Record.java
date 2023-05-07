package com.example.aop.Annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.UUID;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Record {

    Class<?> Value();
}
