package com.example.aop.Trigger.FieldTrigger;

import com.example.aop.Annotation.FieldTrigger;
import com.example.aop.Annotation.Trigger;
import com.example.aop.System.SystemTriggerType;
import com.example.aop.Table.TestTable;

@FieldTrigger(tablename = TestTable.class)
public class TestTrigger {

    @Trigger(RaisedFor = SystemTriggerType.Validate)
    public void CheckIfAddressIsIIegel() {
        System.out.println("Check if this address is iiegel..............");
    }

    public void doCheckIfAddressIsIIegel() {}
}
