package com.hello.controller;

import com.hello.entity.HelloWorldBean;
import com.hello.environment.InstanceInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private InstanceInformationService instanceInformationService;

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;


    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World V10!!! " + instanceInformationService.retrieveInstanceInfo();
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Bean !!!");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){

        return new HelloWorldBean(String.format("Hello, %s",name));
    }
}
