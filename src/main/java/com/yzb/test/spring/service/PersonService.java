package com.yzb.test.spring.service;

import com.yzb.test.spring.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();

    int insertPerson1();

    int insertPerson2();
}
