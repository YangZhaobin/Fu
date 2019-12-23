package com.yzb.test;

import com.yzb.test.spring.mapper.PersonMapper;
import com.yzb.test.spring.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {

    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;

    @Test
    public void test1() {
//        personService.insertPerson1();

        System.out.println(personMapper.find(1, "yang"));

    }
}
