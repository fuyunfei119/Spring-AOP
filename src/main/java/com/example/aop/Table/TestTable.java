package com.example.aop.Table;

import com.example.aop.Annotation.Entity;
import com.example.aop.Annotation.OnValidate;
import com.example.aop.Annotation.TableNameConstants;
import com.example.aop.System.TriggerType;
import lombok.experimental.FieldNameConstants;


@FieldNameConstants(asEnum = true)
@TableNameConstants
@Entity
public class TestTable {

    private String CustomerNo;
    @OnValidate(Value = TriggerType.CheckIfAddressIsIIegel)
    private String Address;
    private String PhoneNo;
}
