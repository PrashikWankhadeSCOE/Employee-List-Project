package com.example.employeeList.controller;

import com.example.employeeList.dto.EmployeeDTO;
import com.example.employeeList.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // 🔹 In-memory storage (acts like DB)
    private final List<EmployeeDTO> employeeList = new ArrayList<>();

    // 🔹 Auto-increment ID
    private Long counter = 1L;

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        employeeDTO.setId(counter++);
        employeeList.add(employeeDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Employee created successfully", employeeDTO));
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Employees fetched successfully", employeeList)
        );
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable Long id) {

        for (EmployeeDTO emp : employeeList) {
            if (emp.getId().equals(id)) {
                return ResponseEntity.ok(
                        new ApiResponse<>(true, "Employee fetched successfully", emp)
                );
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "Employee not found", null));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO updatedEmployee) {

        for (EmployeeDTO emp : employeeList) {
            if (emp.getId().equals(id)) {

                emp.setName(updatedEmployee.getName());
                emp.setEmail(updatedEmployee.getEmail());
                emp.setAccount(updatedEmployee.getAccount());
                emp.setSalary(updatedEmployee.getSalary());

                return ResponseEntity.ok(
                        new ApiResponse<>(true, "Employee updated successfully", emp)
                );
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "Employee not found", null));
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long id) {

        boolean removed = employeeList.removeIf(emp -> emp.getId().equals(id));

        if (removed) {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Employee deleted successfully", null)
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "Employee not found", null));
    }
}
