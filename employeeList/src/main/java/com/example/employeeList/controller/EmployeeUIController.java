package com.example.employeeList.controller;

import com.example.employeeList.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/ui/employees")
public class EmployeeUIController {

    // In-memory storage for UI (can be replaced with DB later)
    private final List<EmployeeDTO> employeeList = new ArrayList<>();

    // Auto-increment ID for UI entries
    private final AtomicLong counter = new AtomicLong(1L);

    // ================= VIEW EMPLOYEES =================
    @GetMapping
    public String viewEmployees(Model model) {
        model.addAttribute("employees", employeeList);
        model.addAttribute("employee", new EmployeeDTO());
        return "employees"; // Thymeleaf template: employees.html
    }

    // ================= ADD EMPLOYEE =================
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        employeeDTO.setId(counter.getAndIncrement());
        employeeList.add(employeeDTO);
        return "redirect:/ui/employees";
    }

    // ================= DELETE EMPLOYEE =================
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeList.removeIf(emp -> emp.getId().equals(id));
        return "redirect:/ui/employees";
    }
}
