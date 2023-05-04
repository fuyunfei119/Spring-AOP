package com.example.aop.Config;

import com.example.aop.Annotation.FieldTrigger;
import com.example.aop.Annotation.TableNameConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(value = "com.example.aop.Table",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = TableNameConstants.class)})
public class EntityScan {
}
