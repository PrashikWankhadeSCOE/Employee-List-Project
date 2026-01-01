package com.example.employeeList.controller;

import com.example.employeeList.dto.EmployeeDTO;
import com.example.employeeList.dto.ApiResponse;
import com.example.employeeList.entity.Employee;
import com.example.employeeList.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> create(@RequestBody Employee employee) {
        Employee saved = repository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Employee saved", saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Employees fetched", repository.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(emp -> ResponseEntity.ok(new ApiResponse<>(true, "Found", emp)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "Not found", null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> update(
            @PathVariable Long id,
            @Valid @RequestBody Employee updatedEmployee) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setFirstName(updatedEmployee.getFirstName());
                    existing.setLastName(updatedEmployee.getLastName());
                    existing.setEmail(updatedEmployee.getEmail());
                    existing.setPhone(updatedEmployee.getPhone());
                    existing.setDepartment(updatedEmployee.getDepartment());
                    existing.setDesignation(updatedEmployee.getDesignation());
                    existing.setDateOfJoining(updatedEmployee.getDateOfJoining());
                    existing.setSalary(updatedEmployee.getSalary());

                    Employee saved = repository.save(existing);

                    return ResponseEntity.ok(
                            new ApiResponse<>(true, "Employee updated", saved)
                    );
                })
                .orElse(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(false, "Employee not found", null))
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted", null));
    }
}