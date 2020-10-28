package com.test.annotation.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * 使用@Value赋值
 *      1.基本的数值
 *      2.可以写SpEL: #{}
 *      3.可以写${}：取出配置文件中的值（在运行环境变量里面的值）
 */
public class Computer {
    @Value("ThinkPad")
    private String brand;
    @Value("#{17-2}")
    private String size;
    @Value("${computer.cpu}")
    private String cpu;
    @Value("${computer.memory}")
    private String memory;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                ", cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
