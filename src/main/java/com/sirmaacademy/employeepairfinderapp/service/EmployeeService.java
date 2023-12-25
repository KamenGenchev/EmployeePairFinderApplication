package com.sirmaacademy.employeepairfinderapp.service;

import com.sirmaacademy.employeepairfinderapp.model.Employee;
import com.sirmaacademy.employeepairfinderapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Employee with this ID: " + id));
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}



