package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImplement;

import jakarta.websocket.server.PathParam;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

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

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // @GetMapping("{employeeId}")
    // public List<Employee> getEmployeeById(@PathVariable("employeeId") Long
    // employeeId) {
    // return employeeService.getEmployeeById(employeeId);
    // }

    @DeleteMapping(path = "{employeeId}")
    public @ResponseBody String deleteEmployee(
            @PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "Deleted";
    }

    // @PutMapping(path = "{employeeId}")
    // public @ResponseBody String updateEmployee(
    // @PathVariable("employeeId") Long employeeId,
    // @RequestBody Employee employee) {
    // employee = employeeService.updateEmployee(employeeId,employee);
    // return "Updated";
    // }
    @PutMapping(path = "{employeeId}")
    public @ResponseBody String updateEmployee(
            @PathVariable("employeeId") Long employeeId,
            @RequestParam Map<String, String> requestParams) throws Exception {
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
                .build();
        employee = employeeService.updateEmployee(employeeId, employee);
        return "Updated";
    }
}