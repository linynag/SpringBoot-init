package com.demo.springbootinit.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class testJava8Stream {


    private static List<Person> initPerson() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("张三", 8, 3000));
        personList.add(new Person("李四", 18, 5000));
        personList.add(new Person("王五28", 28, 7000));
        personList.add(new Person("王五27", 27, 7000));
        personList.add(new Person("孙六", 38, 9000));
        return personList;
    }


    public static void main(String[] args) {
        // 1、从员工集合中筛选出salary大于8000的员工，并放置到新的集合里。
        List<Person> collect = initPerson().stream().filter(person -> person.getSalary() > 8000).collect(Collectors.toList());
        log.info("salary大于8000的员工 {}", collect);

        // 2、统计员工的最高薪资、平均薪资、薪资之和。
        // 最高薪资
        Person person = initPerson().stream().max(Comparator.comparing(Person::getSalary)).orElse(null);
        log.info("最高薪资 {}", person.getSalary());
        //  平均薪资
        Double aveSalary = initPerson().stream().collect(Collectors.averagingDouble(Person::getSalary));
        log.info("平均薪资 {}", aveSalary);
        // 工资求和
        Optional<Integer> sumSalary = initPerson().stream().map(Person::getSalary).reduce(Integer::sum);
        log.info("工资求和 {}", sumSalary);


        // 3.1 薪资降序 年龄升序
        List<Person> personList = initPerson().stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p1.getSalary() - p2.getSalary();
            }
        }).collect(Collectors.toList());
        log.info("薪资降序 年龄升序 {}", personList);

        // 3.2 薪资升序 年龄升序
        List<Person> collect1 = initPerson().stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).collect(Collectors.toList());
        log.info(" 薪资升序 年龄升序 {}", collect1);
        // 4、将员工按性别分类，将员工按性别和地区分类，将员工按薪资是否高于8000分为两部分。

    }


}


