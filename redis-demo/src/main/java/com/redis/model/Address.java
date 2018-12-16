package com.redis.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "addressSeq", sequenceName = "ADD_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "addressSeq")
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false, length = 400)
    private String street;
}
