package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // @Query("SELECT e FROM employee WHERE e.id = ?1")
    Employee findEmployeeById(Long id);

}
