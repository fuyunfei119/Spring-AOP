package com.example.aop.Config;

import com.example.aop.Annotation.Entity;
import com.example.aop.Annotation.FieldTrigger;
import com.example.aop.Annotation.Trigger;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class EntityScanProcessor implements BeanFactoryPostProcessor {
    List<String> trigger = new ArrayList<>();

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        Collection<Object> beans = beanFactory.getBeansWithAnnotation(FieldTrigger.class).values();

        for (Object bean : beans) {
            ReflectionUtils.doWithMethods(bean.getClass(), method -> {
                if (AnnotationUtils.findAnnotation(method, Trigger.class) != null) {
                    trigger.add(method.getName());
                }
            });
        }

        System.out.println(this.getClass().getPackageName());

        System.out.println(trigger);

        File directory = new File("src/main/java/"+"com/example/aop/System");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, "TriggerType" + ".java");
        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();

        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println("package " + "com.example.aop.System" + ";");
        writer.println();
        writer.println("public enum " + "TriggerType" + " {");

        for (String method : trigger) {
            writer.println("\t" + method + ",");
        }

        writer.println("}");
        writer.close();
    }
}
