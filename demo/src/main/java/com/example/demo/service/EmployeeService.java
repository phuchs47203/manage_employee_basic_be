package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
    Employee addEmployee(Employee employee) throws Exception;

    List<Employee> getEmployees();
}
