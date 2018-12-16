package com.redis.service;


import com.redis.model.Person;
import com.redis.repository.jpa.PersonRepository;
import com.redis.repository.redis.PersonRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private static final int RETRY = 3;

    @Autowired
    private PersonRedisRepository personRedisRepository;

    @Autowired
    private PersonRepository personRepository;

    public void savePerson(Person person) {
        personRedisRepository.save(person);
        try {
            personRepository.save(person);
        } catch (Exception e) {
            retryAndRollBack(person);
        }
    }

    private void retryAndRollBack(Person person) {
        int retryCount = 1;
        boolean isRollback = true;
        while (isRollback && retryCount <= RETRY) {
            try {
                personRepository.save(person);
                isRollback = false;
            } catch (Exception e) {
                isRollback = true;
            }
            retryCount++;
        }

        if (isRollback) {
            personRedisRepository.delete(person);
        }
    }


    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        personRedisRepository.findAll().forEach(persons::add);
        return persons;
    }

    public void deleteAll() {
        personRedisRepository.deleteAll();
    }

    public void deleteById(Long id) {
        personRedisRepository.delete(id);
    }
}
