package com.example.aop.Table;

import com.example.aop.Annotation.OnValidate;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

@FieldNameConstants(asEnum = true)
public class TestTable {

    private String CustomerNo;
    @OnValidate(Value = "CheckValidateAddress")
    private String Address;
    private String PhoneNo;
}
