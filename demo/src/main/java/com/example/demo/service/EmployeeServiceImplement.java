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
}
