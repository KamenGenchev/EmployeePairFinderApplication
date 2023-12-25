package com.sirmaacademy.employeepairfinderapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class EmployeePair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Employee emp1;
    @Transient
    private Employee emp2;
    private Long projectID;
    private int durationInDays;
    private LocalDate startDate;
    private LocalDate endDate;


    public EmployeePair(Employee employee1, Employee employee2, Long projectID) {
        this.emp1 = employee1;
        this.emp2 = employee2;
        this.projectID = projectID;
        durationInDays = 0;
    }

    public EmployeePair() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmp1() {
        return emp1;
    }

    public void setEmp1(Employee emp1) {
        this.emp1 = emp1;
    }

    public Employee getEmp2() {
        return emp2;
    }

    public void setEmp2(Employee emp2) {
        this.emp2 = emp2;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
