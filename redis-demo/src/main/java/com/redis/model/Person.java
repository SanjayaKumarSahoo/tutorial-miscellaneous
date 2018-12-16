package com.redis.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@RedisHash("persons")
@Getter
@Setter
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "customerSeq", sequenceName = "CUST_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "customerSeq")
    @Column(name = "PERSON_ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToOne(fetch = LAZY, cascade = {ALL})
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
}
