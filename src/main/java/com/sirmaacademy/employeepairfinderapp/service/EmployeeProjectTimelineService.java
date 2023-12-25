package com.sirmaacademy.employeepairfinderapp.service;

import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;
import com.sirmaacademy.employeepairfinderapp.repository.EmployeeProjectTimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProjectTimelineService {
    private final EmployeeProjectTimelineRepository repository;
    @Autowired
    public EmployeeProjectTimelineService(EmployeeProjectTimelineRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeProjectTimeline> findAll() {
        return repository.findAll();
    }

    public EmployeeProjectTimeline findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Employee with this ID: " + id));
    }

    public EmployeeProjectTimeline save(EmployeeProjectTimeline timeline) {
        return repository.save(timeline);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    public void update(Long id, EmployeeProjectTimeline updatedTimeline) {
        EmployeeProjectTimeline existingTimeline = repository.findById(id).orElse(null);
        if (existingTimeline == null) {
            throw new IllegalArgumentException("Employee not found with ID: " + id);
        }

        existingTimeline.setEmployee(updatedTimeline.getEmployee());
        existingTimeline.setProjectID(updatedTimeline.getProjectID());
        existingTimeline.setStartDate(updatedTimeline.getStartDate());
        existingTimeline.setEndDate(updatedTimeline.getEndDate());

        // Save the updated employee
        repository.save(existingTimeline);
    }

}

