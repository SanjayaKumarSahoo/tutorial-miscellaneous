package com.redis.repositoty.jpa;

import com.redis.model.Address;
import com.redis.model.Person;
import com.redis.repository.jpa.AddressRepository;
import com.redis.repository.jpa.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldSavePerson() {
        // given
        Address address = new Address();
        address.setCity("city");
        address.setStreet("street");
        Address saved = addressRepository.save(address);

        Person person = new Person();
        person.setAddress(saved);
        person.setFirstName("firstName");
        person.setLastName("lastName");

        // when
        Person savedPerson = personRepository.save(person);

        assertNotNull(savedPerson.getId());
    }
}

