package com.sirmaacademy.employeepairfinderapp.repository;

import com.sirmaacademy.employeepairfinderapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
