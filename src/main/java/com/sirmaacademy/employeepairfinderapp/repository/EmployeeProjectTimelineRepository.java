package com.sirmaacademy.employeepairfinderapp.repository;

import com.sirmaacademy.employeepairfinderapp.model.Employee;
import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EmployeeProjectTimelineRepository extends JpaRepository<EmployeeProjectTimeline, Long> {
    boolean existsByEmployeeAndProjectIdAndStartDateAndEndDate(
            Employee employee, Long projectId, LocalDate startDate, LocalDate endDate);
}

