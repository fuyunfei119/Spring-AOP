package com.example.aop.Trigger.TableTrigger;

import com.example.aop.Annotation.TableTrigger;
import com.example.aop.Annotation.Trigger;
import com.example.aop.System.SystemTriggerType;
import com.example.aop.Table.TestTable;

@TableTrigger(CLASS = TestTable.class)
public class TestTableTrigger {

    @Trigger(RaisedFor = SystemTriggerType.Insert)
    public void OnCheckEntityResultOnBeforeInsert() {
        System.out.println("this is an healthy entity ..............");
    }

    @Trigger(RaisedFor = SystemTriggerType.Init)
    public void OnInitEntity() {
        System.out.println("Initialization ..............");
    }

    @Trigger(RaisedFor = SystemTriggerType.Modify)
    public void OnModifyEntity() {
        System.out.println("This Entity can be modify");
    }

    @Trigger(RaisedFor = SystemTriggerType.Delete)
    public void OnCheckEntityResultOnBeforeDelete() {
        System.out.println("delete entity ..............");
    }
}
