package com.springboottesting1.testspringboot1.service;

import com.springboottesting1.testspringboot1.Repository.EmployeeRepository;
import com.springboottesting1.testspringboot1.exception.ResourceNotFoundException;
import com.springboottesting1.testspringboot1.modal.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedeEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedeEmployee.isPresent())
            throw new ResourceNotFoundException("Employee not found with id "+employee.getEmail());
        return employeeRepository.save(employee);

    }
}
