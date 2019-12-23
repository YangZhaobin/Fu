package com.yzb.test.spring.service.impl;

import com.yzb.test.spring.entity.Person;
import com.yzb.test.spring.mapper.PersonMapper;
import com.yzb.test.spring.service.PersonService;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public List<Person> getAll() {
        return personMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertPerson1() {
        Person person = new Person();
        person.setName("test1")
                .setAge(23);
//                .setEmail("testEmail1");
        int ret = personMapper.insert(person);
        try {
            ((PersonService) AopContext.currentProxy()).insertPerson2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int insertPerson2() {
        Person person = new Person();
        person.setName("test2")
                .setAge(24);
//                .setEmail("testEmail2");
        int ret = personMapper.insert(person);
        int i = 10 / 0;
        return ret;
    }
}
