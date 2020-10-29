package com.test.aop.function;

public class AopFunction {

    public String testAop(String name, String email) {
        System.out.println("[AopFunction] => test " + name + email);
        return "success";
    }
}
