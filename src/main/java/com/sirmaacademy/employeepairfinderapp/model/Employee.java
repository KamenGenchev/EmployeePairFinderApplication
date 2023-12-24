package com.sirmaacademy.employeepairfinderapp.model;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    private Long id;

    public Employee(Long id) {
        this.id = id;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
