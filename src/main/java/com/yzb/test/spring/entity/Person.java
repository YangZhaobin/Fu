package com.yzb.test.spring.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "person")
public class Person {
    public interface PersonView {}

    @Id
    String id;

    @JsonView(PersonView.class)
    String name;

    @JsonView(PersonView.class)
    Integer age;

//    String email;

    String test;
}
