package com.sirmaacademy.employeepairfinderapp.csvreader;

import com.sirmaacademy.employeepairfinderapp.model.Employee;
import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;
import com.sirmaacademy.employeepairfinderapp.repository.EmployeeProjectTimelineRepository;
import com.sirmaacademy.employeepairfinderapp.repository.EmployeeRepository;
import com.sirmaacademy.employeepairfinderapp.util.DateFormats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class CsvDataPopulation {
    private final CsvReader csvReader;
    private final EmployeeProjectTimelineRepository employeeProjectTimelineRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public CsvDataPopulation(CsvReader csvReader, EmployeeProjectTimelineRepository employeeProjectTimelineRepository, EmployeeRepository employeeRepository) {
        this.csvReader = csvReader;
        this.employeeProjectTimelineRepository = employeeProjectTimelineRepository;
        this.employeeRepository = employeeRepository;
    }
    public void getData() {
        File file = null;
        try {
            file = new ClassPathResource("workTimelines.csv").getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String[]> employeeTimelineData = csvReader.readDataFile(file);

        for (String[] row : employeeTimelineData) {
            Long employeeId = Long.parseLong(row[0].trim());
            Long projectId = Long.parseLong(row[1].trim());
            LocalDate startDate = DateFormats.convertDate(row[2].trim());
            LocalDate endDate = DateFormats.convertDate(row[3].trim());

            Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
            Employee employee = existingEmployee.orElseGet(() -> {
                Employee newEmployee = new Employee(employeeId);
                return employeeRepository.save(newEmployee);
            });

            if (!employeeProjectTimelineRepository.existsByEmployeeAndProjectIdAndStartDateAndEndDate(
                    employee, projectId, startDate, endDate)) {

                EmployeeProjectTimeline timeline = new EmployeeProjectTimeline(employee, projectId, startDate, endDate);
                employeeProjectTimelineRepository.save(timeline);
            }
            }
        }
    }

