package com.test.aop.po;

import com.test.aop.annotion.Log;
import com.test.aop.annotion.LogParam;
@LogParam
public class User {
    @Log
    public void show(){
        System.out.println("[ User ] show ");
    }

    public void run(){
        System.out.println("[ User ] run ");
    }

    public void test( String method){
        System.out.println("[ User ] test => " + method);
    }
    public void config(UserParam userParam){
        System.out.println("[ User ] config => " + userParam.getName());
    }
}
