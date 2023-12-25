package com.sirmaacademy.employeepairfinderapp.controller;

import com.sirmaacademy.employeepairfinderapp.model.EmployeeProjectTimeline;
import com.sirmaacademy.employeepairfinderapp.service.EmployeeProjectTimelineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/timelines")
public class EmployeeProjectTimelineController {
    private final EmployeeProjectTimelineService service;
    @Autowired
    public EmployeeProjectTimelineController(EmployeeProjectTimelineService service) {
        this.service = service;
    }

    @GetMapping
    public String  listEmployeeProjectTimelines(Model model) {
        List<EmployeeProjectTimeline> timelines = service.findAll();
        model.addAttribute("timelines", timelines);
        return "timelines";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public EmployeeProjectTimeline getEmployeeProjectTimelineById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("timeline", new EmployeeProjectTimeline());
        return "timelines/add";
    }
    @PostMapping("/add")
    public String addEmployeeProjectTimeline(@ModelAttribute @Valid EmployeeProjectTimeline timeline, BindingResult result) {
        if (result.hasErrors()) {
            return "/timelines/add";
        }
        service.save(timeline);
        return "redirect:/timelines";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        EmployeeProjectTimeline timeline = service.findById(id);
        model.addAttribute("timeline", timeline);
        return "timelines/edit";
    }

    @PostMapping("/edit/{id}")
    public String editTimeline(@PathVariable Long id, @ModelAttribute @Valid EmployeeProjectTimeline timeline, BindingResult result) {
        if (result.hasErrors()) {
            return "timelines/edit";
        }
        service.update(id, timeline);
        return "redirect:/timelines";
    }

    @DeleteMapping("/{id}")
    public String deleteTimeline(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/timelines";
    }

}
