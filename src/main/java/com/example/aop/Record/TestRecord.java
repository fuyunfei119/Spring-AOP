package com.example.aop.Record;

import com.example.aop.Table.TestTable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class TestRecord<E> {

    private String UUID;
    private Class<?> aClass;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public TestRecord() {
        this.UUID = java.util.UUID.randomUUID().toString();
    }

    public <T> TestRecord<E> SetSource(Class<T> aClass) {
        this.aClass = aClass;
        return this;
    }

    public TestRecord<E> GetClassType() { return this; }

    public TestRecord<E> Init() { return this; };

    public TestRecord<E> Validate(E field, String newValue) { return this; };

    public TestRecord<E> SetLoadFields() { return this; };

    public TestRecord<E> SetRange() { return this;};

    public TestRecord<E> SetFilter() { return this; };

    public TestRecord<E> FindSet() { return this; };

    public TestRecord<E> Modify() { return this; };

    public TestRecord<E> Delete() { return this; };

    public TestRecord<E> Insert() { return this; };

}
