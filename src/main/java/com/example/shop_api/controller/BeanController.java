package com.example.shop_api.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {
    private final ListableBeanFactory beanFactory;

    public BeanController(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @GetMapping("/beans")
    public String[] getBeans() {
        return beanFactory.getBeanDefinitionNames();
    }
}
