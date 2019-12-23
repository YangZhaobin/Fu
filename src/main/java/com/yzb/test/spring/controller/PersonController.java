package com.yzb.test.spring.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yzb.test.spring.entity.Person;
import com.yzb.test.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("persons")
    @JsonView(Person.PersonView.class)
    public ResponseEntity getAllPerson() {
        return ResponseEntity.ok(personService.getAll());
    }

}
