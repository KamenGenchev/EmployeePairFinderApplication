package com.sirmaacademy.employeepairfinderapp;

import com.sirmaacademy.employeepairfinderapp.model.Employee;
import com.sirmaacademy.employeepairfinderapp.model.EmployeeCollabPair;
import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LongestPairFinder {
    public String getLongestPair(List<EmployeeProjectTimeline> timelines, Long projectId) {
        EmployeeCollabPair longestPair = findLongestEmployeePair(getProjectSpecificTimelines(timelines, projectId));

        if (longestPair.getEmployeePairList().isEmpty()) {
            return "No overlapping employee pairs found for Project " + projectId + ".";
        }

        return "Employee Pair with Overlapping Duration for Project " + projectId + ": " +
                longestPair.getEmployeePairList() + " Duration: " + longestPair.getDurationInDays();

    }

    private EmployeeCollabPair findLongestEmployeePair(List<EmployeeProjectTimeline> timelines) {
        timelines.sort(Comparator
                .comparing(EmployeeProjectTimeline::getStartDate)
                .thenComparing(EmployeeProjectTimeline::getEndDate));

        EmployeeCollabPair employeeCollabPair = new EmployeeCollabPair();
        Map<Long, List<Employee>> projectEmployeesMap = new HashMap<>();
        LocalDate currentDate = LocalDate.now();

        for (EmployeeProjectTimeline timeline : timelines) {
            LocalDate startDate = timeline.getStartDate();
            LocalDate endDate = timeline.getEndDate();

            if (startDate.isAfter(currentDate)) {
                projectEmployeesMap.clear();
                currentDate = endDate.plusDays(1);
            }

            projectEmployeesMap
                    .computeIfAbsent(timeline.getProjectID(), n -> new ArrayList<>())
                    .add(timeline.getEmployee());

            List<Employee> currentEmployees = projectEmployeesMap.get(timeline.getProjectID());
            if (currentEmployees.size() > 1) {
                int duration = getDateAsInt(currentDate) - getDateAsInt(startDate);
                employeeCollabPair =
                        new EmployeeCollabPair(new ArrayList<>(currentEmployees), timeline.getProjectID(), duration);

                List<Employee> overlappingEmployees = new ArrayList<>(currentEmployees);
                for (EmployeeProjectTimeline nextTimeline : timelines) {
                    if (nextTimeline.getStartDate().isAfter(currentDate)) {
                        break;
                    }
                    if (nextTimeline.getProjectID().equals(timeline.getProjectID())) {
                        overlappingEmployees.add(nextTimeline.getEmployee());
                    }
                }
                currentEmployees.retainAll(overlappingEmployees);
            }
        }

        return employeeCollabPair;
    }

    private int getDateAsInt(LocalDate localDate) {
        int dateInt = 0;
        dateInt = localDate.getYear() + localDate.getDayOfYear();
        return dateInt;
    }

    private List<EmployeeProjectTimeline> getProjectSpecificTimelines(List<EmployeeProjectTimeline> allTimelines, Long projectId) {
        return allTimelines.stream()
                .filter(p -> Objects.equals(p.getProjectID(), projectId))
                .collect(Collectors.toList());
    }
}
