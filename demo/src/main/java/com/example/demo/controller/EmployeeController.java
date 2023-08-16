package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImplement;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImplement employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public Employee addEmployee(@RequestParam Map<String, String> requestParams) throws Exception {
        String fullNameString = requestParams.get("fullName");
        String emailString = requestParams.get("email");
        String departmentString = requestParams.get("department");
        String positionString = requestParams.get("position");
        String addressString = requestParams.get("address");
        String file = requestParams.get("file");

        Employee employee = Employee.builder()
                .file(file)
                .fullNameString(fullNameString)
                .emailString(emailString)
                .departmentString(departmentString)
                .positionString(positionString)
                .addressString(addressString)
                .timeStamp(new Date().toString())
                .build();
        employee = employeeService.addEmployee(employee);
        return employee;
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
