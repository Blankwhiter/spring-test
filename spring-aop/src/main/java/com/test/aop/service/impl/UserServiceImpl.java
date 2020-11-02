package com.test.aop.service.impl;

import com.test.aop.service.BaseService;

public class UserServiceImpl implements BaseService {
    @Override
    public void baseMethod() {
        System.out.println("[ UserServiceImpl ]   baseMethod ");
    }
}
