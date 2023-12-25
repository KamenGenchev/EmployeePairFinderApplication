package com.sirmaacademy.employeepairfinderapp.controller;

import com.sirmaacademy.employeepairfinderapp.model.Employee;
import com.sirmaacademy.employeepairfinderapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeAdd";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employeeAdd";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employeeAdd";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, @ModelAttribute @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employeeAdd";
        }
        return "redirect:/employees";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/emplyees";
    }
}
