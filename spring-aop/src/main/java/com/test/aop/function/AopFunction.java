package com.test.aop.function;

public class AopFunction {

    public String testAop(String name, String email) {
        System.out.println("[AopFunction] =>  testAop " + name + email);
        return "testAop success";
    }


    public String testMethodExecution(String expression){
        System.out.println("[AopFunction] =>  String " + expression);
        return "testPrivateExecution success";
    }

    public int testMethodExecution(int val,String expression){
        System.out.println("[AopFunction] =>  int  String" + expression);
        return val;
    }

    public Double testMethodExecution(Double val,String expression){
        System.out.println("[AopFunction] =>  Double String " + expression);
        return val;
    }


    public Boolean testMethodExecution(Boolean val ){
        System.out.println("[AopFunction] =>  Boolean " );
        return val;
    }
    public Float testMethodExecution(Float val){
        System.out.println("[AopFunction] =>  Float ");
        return val;
    }


}
