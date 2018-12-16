package com.entity;


import com.couchbase.client.java.repository.annotation.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@Getter
public class Order {

    @Id
    private final String id;

    @Field
    private final List<Product> products;


}
