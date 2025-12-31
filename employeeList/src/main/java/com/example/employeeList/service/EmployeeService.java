package com.example.employeeList.service;

import com.example.employeeList.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final Map<Long, EmployeeDTO> employeeMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        long id = idGenerator.getAndIncrement();
        employeeDTO.setId(id);
        employeeMap.put(id, employeeDTO);
        return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return employeeMap.get(id);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        employeeMap.put(id, employeeDTO);
        return employeeDTO;
    }

    public void deleteEmployee(Long id) {
        employeeMap.remove(id);
    }
}
