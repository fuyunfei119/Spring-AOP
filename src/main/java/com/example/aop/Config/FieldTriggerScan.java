package com.example.aop.Config;

import com.example.aop.Annotation.FieldTrigger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(value = "com.example.aop.Trigger.*",
                includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = FieldTrigger.class)})
public class FieldTriggerScan {
}
