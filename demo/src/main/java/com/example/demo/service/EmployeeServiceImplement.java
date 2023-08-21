package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplement implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImplement(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) throws Exception {
        try {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            BeanUtils.copyProperties(employee, employeeEntity);
            if (employee.getFile() != null && !employee.getFile().equalsIgnoreCase(null)) {
                employeeEntity.setProfilePictureString(employee.getFile());
            } else {
                employeeEntity.setProfilePictureString(null);
            }
            employeeEntity = employeeRepository.save(employeeEntity);
            employee.setId(employeeEntity.getId());
            employee.setFile(null);
            employee.setPositionString(employeeEntity.getProfilePictureString());
        } catch (Exception e) {
            throw new Exception("Could not save Employee " + e);
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        employees = employeeEntities.stream()
                .map((employeeEntity) -> Employee.builder()
                        .id(employeeEntity.getId())
                        .timeStamp(employeeEntity.getTimeStamp())
                        .fullNameString(employeeEntity.getFullNameString())
                        .emailString(employeeEntity.getEmailString())
                        .departmentString(employeeEntity.getDepartmentString())
                        .positionString(employeeEntity.getPositionString())
                        .addressString(employeeEntity.getAddressString())
                        .ProfilePictureString(employeeEntity.getProfilePictureString())
                        .build())
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public String deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + employeeId + " does not exists");
        }
        employeeRepository.deleteById(employeeId);
        return "deleted";
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        employeeEntity.setEmailString(employee.getEmailString());
        employeeEntity.setAddressString(employee.getAddressString());
        employeeEntity.setDepartmentString(employee.getDepartmentString());
        employeeEntity.setFullNameString(employee.getFullNameString());
        employeeEntity.setPositionString(employee.getPositionString());
        employeeEntity.setProfilePictureString(employee.getFile());
        employeeRepository.save(employeeEntity);
        employee.setFile(null);
        employee.setPositionString(employeeEntity.getProfilePictureString());
        return employee;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employees = new Employee();
        employees.setId(employeeEntity.getId());
        employees.setFullNameString(employeeEntity.getFullNameString());
        employees.setEmailString(employeeEntity.getEmailString());
        employees.setDepartmentString(employeeEntity.getDepartmentString());
        employees.setPositionString(employeeEntity.getPositionString());
        employees.setAddressString(employeeEntity.getAddressString());
        employees.setProfilePictureString(employeeEntity.getProfilePictureString());
        employees.setTimeStamp(employeeEntity.getTimeStamp());
        return employees;
    }

    // @Override
    // public List<Employee> getEmployeeById(Long id) {
    // EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
    // Employee employees = new Employee();
    // employees.setFullNameString(employeeEntity.getFullNameString());
    // employees.setEmailString(employeeEntity.getEmailString());
    // employees.setDepartmentString(employeeEntity.getDepartmentString());
    // employees.setPositionString(employeeEntity.getPositionString());
    // employees.setAddressString(employeeEntity.getAddressString());
    // employees.setProfilePictureString(employeeEntity.getProfilePictureString());
    // employees.setTimeStamp(employeeEntity.getTimeStamp());
    // return employees;

    // List<EmployeeEntity> employeeEntities = new ArrayList<>();
    // employeeEntities.add(employeeRepository.findById(id).get());
    // List<Employee> employees = new ArrayList<>();
    // employees = employeeEntities.stream()
    // .map((employeeEntity) -> Employee.builder()
    // .id(employeeEntity.getId())
    // .timeStamp(employeeEntity.getTimeStamp())
    // .fullNameString(employeeEntity.getFullNameString())
    // .emailString(employeeEntity.getEmailString())
    // .departmentString(employeeEntity.getDepartmentString())
    // .positionString(employeeEntity.getPositionString())
    // .addressString(employeeEntity.getAddressString())
    // .ProfilePictureString(employeeEntity.getProfilePictureString())
    // .build())
    // .collect(Collectors.toList());
    // return employees;
    // }

}
