package com.example.aop.Service;

import com.example.aop.Annotation.TableNameConstants;
import com.example.aop.Config.EntityScan;
import com.example.aop.Config.TriggerScan;
import com.example.aop.Record.TestRecord;
import com.example.aop.Table.TestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TestService {

    private final TestRecord<TestTable.Fields> testRecord;
    private final TestRecord<TestTable.Fields> testRecord2;
    private final TestRecord<TestTable.Fields> testRecord3;

    public TestService(TestRecord<TestTable.Fields> testRecord, TestRecord<TestTable.Fields> testRecord2, TestRecord<TestTable.Fields> testRecord3) {
        this.testRecord = testRecord;
        this.testRecord2 = testRecord2;
        this.testRecord3 = testRecord3;
    }

    public void aopTest() {
        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(EntityScan.class);

        String[] beanDefinitionNames1 = applicationContext1.getBeanDefinitionNames();

//        System.out.println(Arrays.toString(applicationContext1.getBeanNamesForAnnotation(TableNameConstants.class)));
//        System.out.println(applicationContext1.getBeansWithAnnotation(TableNameConstants.class));

    }

    public void aopTest1() {
        testRecord
                .SetLoadFields()
                .SetRange()
                .SetFilter()
                .FindSet()
                .Validate(TestTable.Fields.Address,"China")
                .Modify();
    }

    public void aopTest2() {
        testRecord3
                .Init()
                .Insert()
                .Modify()
                .Delete();
    }
}
