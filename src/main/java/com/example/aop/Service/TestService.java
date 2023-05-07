package com.example.aop.Service;

import com.example.aop.Annotation.CodeUnits;
import com.example.aop.Config.EntityScan;
import com.example.aop.Record.TestRecord;
import com.example.aop.Table.TestTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@CodeUnits
@Service
public class TestService {

    @Autowired
    private TestRecord<TestTable.Fields> testRecord;
    @Autowired
    private TestRecord<TestTable.Fields> testRecord3;
    @Autowired
    private TestRecord<TestTable.Fields> testRecord2;



    public void aopTest() {
        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(EntityScan.class);

        String[] beanDefinitionNames1 = applicationContext1.getBeanDefinitionNames();
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

    public void appTest3() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    }

    public void aopTest2() {
        testRecord3
                .SetSource(TestTable.class)
                .Init()
                .Insert()
                .Modify()
                .Delete();
    }
}
