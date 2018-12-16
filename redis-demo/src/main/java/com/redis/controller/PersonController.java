package com.redis.controller;

import com.redis.model.Person;
import com.redis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, method = POST)
    public void create(@RequestBody Person person) {
        personService.savePerson(person);
    }

    @RequestMapping(value = "/delete/{id}", method = DELETE)
    public void delete(@PathVariable("id") String id) {
        personService.deleteById(Long.valueOf(id));
    }

    @RequestMapping(value = "/all", produces = APPLICATION_JSON_VALUE, method = GET)
    public List<Person> findAll() {
        return personService.findAll();
    }

}
