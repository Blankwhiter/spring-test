package com.test.aop.service.impl;

import com.test.aop.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void testMethod() {
        System.out.println("[ TestServiceImpl ]  testMethod ");
    }
}
