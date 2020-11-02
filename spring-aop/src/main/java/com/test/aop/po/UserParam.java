package com.test.aop.po;

import com.test.aop.annotion.LogParam;

@LogParam
public class UserParam {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
