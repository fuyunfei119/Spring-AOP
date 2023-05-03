package com.example.aop.Controller;

import com.example.aop.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
