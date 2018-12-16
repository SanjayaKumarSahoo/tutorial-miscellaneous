package com.repository;


import com.entity.Order;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface OrderRepository extends CouchbaseRepository<Order, String> {
}
