package com.redis.repositoty.jpa;

import com.redis.model.Address;
import com.redis.repository.jpa.AddressRepository;
import org.junit.Assert;
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
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void shouldSaveAddress() {
        // given
        Address address = new Address();
        address.setCity("city");
        address.setStreet("street");

        // when
        Address saved = addressRepository.save(address);
        assertNotNull(saved.getId());
    }
}
