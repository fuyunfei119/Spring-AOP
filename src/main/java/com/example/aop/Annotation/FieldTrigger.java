package com.example.aop.Annotation;

import com.example.aop.Table.TestTable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FieldTrigger {

    @AliasFor(annotation = Component.class)
    String value() default "";

    Class<?> tablename();
}
