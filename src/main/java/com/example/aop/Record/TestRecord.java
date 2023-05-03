package com.example.aop.Record;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class TestRecord<E extends Enum<E>> {
    public TestRecord<E> Validate(E field, String newValue) { return this; };

    public TestRecord<E> SetLoadFields() { return this; };

    public TestRecord<E> SetRange() { return this;};

    public TestRecord<E> SetFilter() { return this; };

    public TestRecord<E> FindSet() { return this; };

    public TestRecord<E> Modify() { return this; };

}
