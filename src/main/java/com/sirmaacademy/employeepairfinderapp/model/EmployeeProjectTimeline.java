package com.sirmaacademy.employeepairfinderapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class EmployeeProjectTimeline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Employee employee;
    @NotNull
    private Long projectId;
    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    public EmployeeProjectTimeline(Employee employee, Long projectID, LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.projectId = projectID;
        this.startDate = startDate;
        this.endDate = Objects.requireNonNullElseGet(endDate, LocalDate::now);
    }

    public EmployeeProjectTimeline() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getProjectID() {
        return projectId;
    }

    public void setProjectID(Long projectID) {
        this.projectId = projectID;
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
