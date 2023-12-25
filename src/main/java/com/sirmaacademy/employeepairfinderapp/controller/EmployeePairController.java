package com.sirmaacademy.employeepairfinderapp.controller;

import com.sirmaacademy.employeepairfinderapp.model.EmployeePair;
import com.sirmaacademy.employeepairfinderapp.service.EmployeePairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/pairs")
public class EmployeePairController {
    private final EmployeePairService employeePairService;

    @Autowired
    public EmployeePairController(EmployeePairService employeePairService) {
        this.employeePairService = employeePairService;
    }

    @GetMapping
    public String listPairs(Model model) {
        List<EmployeePair> employeePairs = employeePairService.findAll();
        model.addAttribute("pairs", employeePairs);
        return "pairs";
    }

}
