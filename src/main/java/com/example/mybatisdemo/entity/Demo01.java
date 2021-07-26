package com.example.mybatisdemo.entity;

public class Demo01 {



    public String test;


    public String getTest() {
        return test;
    }

    public Demo01() {
    }

    public Demo01(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test='" + test + '\'' +
                '}';
    }
}
