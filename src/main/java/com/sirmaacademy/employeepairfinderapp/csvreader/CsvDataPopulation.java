package com.sirmaacademy.employeepairfinderapp.csvreader;

import com.sirmaacademy.employeepairfinderapp.model.Employee;
import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;
import com.sirmaacademy.employeepairfinderapp.repository.EmployeeProjectTimelineRepository;
import com.sirmaacademy.employeepairfinderapp.util.DateFormats;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@Service
public class CsvDataPopulation {
    private final CsvReader csvReader;
    private final EmployeeProjectTimelineRepository employeeProjectTimelineRepository;

    @Autowired
    public CsvDataPopulation(CsvReader csvReader, EmployeeProjectTimelineRepository employeeProjectTimelineRepository) {
        this.csvReader = csvReader;
        this.employeeProjectTimelineRepository = employeeProjectTimelineRepository;
    }
    @PostConstruct
    public void getData() {
        File file = null;
        try {
            file = new ClassPathResource("workTimelines.csv").getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String[]> employeeTimelineData = csvReader.readDataFile(file);

        for (String[] row : employeeTimelineData) {
            Employee employee = new Employee(Long.parseLong(row[0]));
            Long projectId = Long.parseLong(row[1]);
            LocalDate startDate = DateFormats.convertDate(row[2]);
            LocalDate endDate = DateFormats.convertDate(row[3]);

            EmployeeProjectTimeline timeline = new EmployeeProjectTimeline(employee, projectId, startDate, endDate);
            employeeProjectTimelineRepository.save(timeline);
        }
    }
}
