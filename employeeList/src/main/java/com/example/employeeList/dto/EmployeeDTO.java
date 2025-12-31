package com.example.employeeList.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Double salary;
    private AccountDTO account;
}
