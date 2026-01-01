package com.example.employeeList.controller;

import com.example.employeeList.entity.Employee;
import com.example.employeeList.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/employees")
public class EmployeeUIController {

    private final EmployeeRepository repository;

    public EmployeeUIController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // ================= VIEW EMPLOYEES =================
    @GetMapping
    public String viewEmployees(Model model) {
        model.addAttribute("employees", repository.findAll());
        model.addAttribute("employee", new Employee());
        return "employees";
    }

    // ================= ADD EMPLOYEE =================
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        repository.save(employee);
        return "redirect:/ui/employees";
    }

    // ================= DELETE EMPLOYEE =================
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/ui/employees";
    }
}
