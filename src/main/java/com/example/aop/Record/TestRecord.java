package com.example.aop.Record;

import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

@Repository
@Scope("prototype")
public class TestRecord<E extends Enum<E>> {

    private Class<E> aClass;

    public TestRecord() {
//        System.out.println("========================");
//        System.out.println(this);
//        System.out.println(this.getClass());
//        System.out.println(this.getClass().getTypeName());
//        System.out.println(Arrays.toString(this.getClass().getTypeParameters()));
//        System.out.println("========================");

        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments != null && typeArguments.length > 0) {
                Type typeArgument = typeArguments[0];
                if (typeArgument instanceof Class) {
                    aClass = (Class<E>) typeArgument;
                    System.out.println("this type is ....."+aClass);
                }
            }
        }
    }

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
