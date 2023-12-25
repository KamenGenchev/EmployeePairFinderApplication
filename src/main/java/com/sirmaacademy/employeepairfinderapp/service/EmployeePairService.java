package com.sirmaacademy.employeepairfinderapp.service;

import com.sirmaacademy.employeepairfinderapp.model.EmployeePair;
import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;
import com.sirmaacademy.employeepairfinderapp.pairfinder.LongestPairFinder;
import com.sirmaacademy.employeepairfinderapp.repository.EmployeePairRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class EmployeePairService {
    private final EmployeePairRepository repository;

    private final LongestPairFinder longestPairFinder;
    private final EmployeeProjectTimelineService employeeProjectTimelineService;

    @Autowired
    public EmployeePairService(EmployeePairRepository repository, LongestPairFinder longestPairFinder, EmployeeProjectTimelineService employeeProjectTimelineService) {
        this.repository = repository;
        this.longestPairFinder = longestPairFinder;
        this.employeeProjectTimelineService = employeeProjectTimelineService;
    }

    public List<EmployeePair> findAll() {
        return repository.findAll();
    }

    public EmployeePair save(EmployeePair pair) {
        return repository.save(pair);
    }


    @PostConstruct
    public void generateAndSavePairs() {
        List<Long> projectIds = new ArrayList<>();
        List<EmployeeProjectTimeline> timelines = employeeProjectTimelineService.findAll();

        for (EmployeeProjectTimeline timeline : timelines) {
            if (!projectIds.contains(timeline.getProjectID())) {
                projectIds.add(timeline.getProjectID());
            }
        }

        for (Long projectId : projectIds) {
            Set<EmployeePair> pairs = longestPairFinder.executeOnProject(timelines, projectId);
            if (pairs.isEmpty()) {
                continue;
            }
            for (EmployeePair pair : pairs) {
                save(pair);
            }
        }
    }


}
