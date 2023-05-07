package com.example.aop.Table;

import com.example.aop.Annotation.*;
import com.example.aop.System.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;


@FieldNameConstants(asEnum = true)
@TableNameConstants
@Entity
@OnInit(value = InitTriggerType.OnInitEntity)
@OnInsert(value = InsertTriggerType.OnCheckEntityResultOnBeforeInsert)
@OnDelete(value = DeleteTriggerType.OnCheckEntityResultOnBeforeDelete)
@OnModify(ModifyTriggerType.OnModifyEntity)
public class TestTable {

    private String CustomerNo;
    @OnValidate(Value = ValidateTriggerType.CheckIfAddressIsIIegel)
    private String Address;
    private String PhoneNo;
}
