package com.example.aop.Trigger.FieldTrigger;

import com.example.aop.Annotation.FieldTrigger;
import com.example.aop.Annotation.Trigger;
import com.example.aop.Table.TestTable;

@FieldTrigger(tablename = TestTable.class)
public class TestTrigger {

    @Trigger
    public static void CheckIfAddressIsIIegel() {
        System.out.println("Check if this address is iiegel..............");
    }
}
