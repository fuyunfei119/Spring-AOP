package com.example.aop.Config;

import com.example.aop.Annotation.CodeUnits;
import com.example.aop.Annotation.TableNameConstants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

@ComponentScan(value = "com.example.aop.Service",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = CodeUnits.class)})
public class RecordScan {
}
