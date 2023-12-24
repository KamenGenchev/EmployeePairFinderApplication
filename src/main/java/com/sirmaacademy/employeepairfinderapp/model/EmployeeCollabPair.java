package com.sirmaacademy.employeepairfinderapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class EmployeeCollabPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Employee> employeePairList;
    private Long projectID;
    private int durationInDays;


    public EmployeeCollabPair(List<Employee> employees, Long projectID, int durationInDays) {
        this.employeePairList = employees;
        this.projectID = projectID;
        this.durationInDays = durationInDays;
    }

    public EmployeeCollabPair() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployeePairList() {
        return employeePairList;
    }

    public void setEmployeePairList(List<Employee> employeePairList) {
        this.employeePairList = employeePairList;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }
}
