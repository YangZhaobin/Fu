package com.yzb.test.spring.mapper;

import com.yzb.test.spring.entity.Person;
import tk.mybatis.mapper.common.Mapper;

public interface PersonMapper extends Mapper<Person> {

    Person find(Integer id, String name);

}
