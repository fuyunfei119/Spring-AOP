package com.example.aop.Config;

import com.example.aop.Annotation.Record;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

@Configuration
public class RecordScanProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        Collection<Object> values = beanFactory.getBeansWithAnnotation(Repository.class).values();

//        if (bean.getClass().isAnnotationPresent(Record.class)) {
//            try {
//                Field uuidField = bean.getClass().getDeclaredField("UUID");
//                uuidField.setAccessible(true);
//                uuidField.set(bean, UUID.randomUUID().toString());
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
