package com.example.aop.Service;

import com.example.aop.Record.TestRecord;
import com.example.aop.Table.TestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRecord<TestTable.Fields> testRecord;
    private final TestRecord<TestTable.Fields> testRecord2;

    @Autowired
    public TestService(TestRecord<TestTable.Fields> testRecord, TestRecord<TestTable.Fields> testRecord2) {
        this.testRecord = testRecord;
        this.testRecord2 = testRecord2;
    }

    public void aopTest() {
        System.out.println("Hello world");
    }

    public void aopTest1() {
        testRecord
                .SetLoadFields()
                .SetRange()
                .SetFilter()
                .FindSet()
                .Validate(TestTable.Fields.PhoneNo,"China")
                .Modify();
    }
}
