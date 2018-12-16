package com.redis.repository.redis;

import com.redis.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<Person, Long> {
}
