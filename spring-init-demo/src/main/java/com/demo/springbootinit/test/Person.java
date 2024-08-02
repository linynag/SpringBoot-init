package com.demo.springbootinit.test;

import lombok.Data;

@Data
public class Person {
    private String name;
    private int age;
    private int salary;

    public Person(String name, int age, int salary) {
        this.name= name;
        this.age=age;
        this.salary=salary;
    }
}
