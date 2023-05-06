package com.example.aop.Table;

import com.example.aop.Annotation.*;
import com.example.aop.System.*;
import lombok.experimental.FieldNameConstants;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


@FieldNameConstants(asEnum = true)
@TableNameConstants
@Entity
@OnInit(value = InitTriggerType.OnInitEntity)
@OnInsert(value = InsertTriggerType.OnCheckEntityResultOnBeforeInsert)
@OnDelete(value = DeleteTriggerType.OnCheckEntityResultOnBeforeDelete)
@OnModify(ModifyTriggerType.OnModifyEntity)
public class TestTable implements ParameterizedType {
    private String CustomerNo;
    @OnValidate(Value = ValidateTriggerType.CheckIfAddressIsIIegel)
    private String Address;
    private String PhoneNo;

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[0];
    }

    @Override
    public Type getRawType() {
        return null;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
