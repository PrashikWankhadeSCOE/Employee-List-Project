package com.example.employeeList.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Employee name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Account is required")
    private String account;

    @Min(value = 1, message = "Experience must be at least 1 year")
    private int experience;

    private double salary;
}
