package com.example.employeeList.controller;

import com.example.employeeList.model.EmployeeModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<EmployeeModel> employees = new ArrayList<>();

    @GetMapping
    public List<EmployeeModel> getAllEmployees() {
        return employees;
    }

    @PostMapping
    public String addEmployee(@RequestBody EmployeeModel employee) {
        employees.add(employee);
        return "Employee added successfully";
    }
}
