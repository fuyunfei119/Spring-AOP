package com.example.aop.Controller;

import com.example.aop.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public void Test1() {
        testService.aopTest();
    }

    @GetMapping("/test")
    public void Test2() { testService.aopTest1(); }

    @GetMapping("/trigger")
    public void Test3() { testService.aopTest2(); }

    @GetMapping("/trigger2")
    public void Test4() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { testService.appTest3(); }
}
