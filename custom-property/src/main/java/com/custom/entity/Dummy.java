package com.custom.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DUMMY")
public class Dummy {

    @Id
    Long id;

    @Column(name = "value")
    private String value;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
