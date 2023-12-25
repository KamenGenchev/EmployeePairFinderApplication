package com.sirmaacademy.employeepairfinderapp.pairfinder;

import com.sirmaacademy.employeepairfinderapp.model.EmployeePair;
import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.*;

@Component
public class LongestPairFinder {
    public Set<EmployeePair> executeOnProject(List<EmployeeProjectTimeline> allTimelines, Long projectId) {
        List<EmployeeProjectTimeline> filteredTimelines = allTimelines
                .stream()
                .filter(p -> Objects.equals(p.getProjectID(), projectId))
                .toList();

        return getLongestPairs(filteredTimelines);
    }

    private Set<EmployeePair> getLongestPairs(List<EmployeeProjectTimeline> timelines) {
        Map<EmployeePair, EmployeePair> employeePairs = new HashMap<>();

        for (EmployeeProjectTimeline timeline1 : timelines) {
            for (EmployeeProjectTimeline timeline2 : timelines) {
                if (periodsOverlap(timeline1, timeline2)) {
                    EmployeePair pair = new EmployeePair(
                            timeline1.getEmployee(), timeline2.getEmployee(), timeline1.getProjectID());

                    if (employeePairs.containsKey(pair)) {
                        long durationInDays = employeePairs.get(pair).getDurationInDays();
                        LocalDate startDate = employeePairs.get(pair).getStartDate();

                        if (timeline1.getStartDate().isBefore(startDate)) {
                            startDate = timeline1.getStartDate();
                        }

                        if (timeline2.getEndDate().isAfter(employeePairs.get(pair).getEndDate())) {
                            employeePairs.get(pair).setEndDate(timeline2.getEndDate());
                        }

                        long daysOverlap = timeline2.getEndDate().toEpochDay() - startDate.toEpochDay() + 1;
                        employeePairs.get(pair).setDurationInDays((int) (durationInDays + daysOverlap));
                    } else {
                        employeePairs.put(pair, pair);
                    }
                }
            }
        }

        EmployeePair longestPair = findLongestPair(employeePairs.values());

        return longestPair != null ? Set.of(longestPair) : Collections.emptySet();
    }

    private boolean periodsOverlap(EmployeeProjectTimeline timeline1, EmployeeProjectTimeline timeline2) {
        return !timeline1.getStartDate().isAfter(timeline2.getEndDate()) &&
                !timeline2.getStartDate().isBefore(timeline1.getStartDate());
    }

    private EmployeePair findLongestPair(Collection<EmployeePair> pairs) {
        EmployeePair longestPair = null;

        for (EmployeePair pair : pairs) {
            if (longestPair == null || pair.getDurationInDays() > longestPair.getDurationInDays()) {
                longestPair = pair;
            }
        }

        return longestPair;
    }
}
