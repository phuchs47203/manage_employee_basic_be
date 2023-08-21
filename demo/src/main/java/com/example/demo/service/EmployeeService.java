package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Employee;

public interface EmployeeService {
    Employee addEmployee(Employee employee) throws Exception;

    List<Employee> getEmployees();

    // List<Employee> getEmployeeById(Long id);
    Employee getEmployeeById(Long id);

    String deleteEmployee(Long id);

    Employee updateEmployee(Long employeeId, Employee employee) throws Exception;
}
