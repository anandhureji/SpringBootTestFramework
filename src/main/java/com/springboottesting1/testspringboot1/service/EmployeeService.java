package com.springboottesting1.testspringboot1.service;

import com.springboottesting1.testspringboot1.modal.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee saveEmployee(Employee employee);


}
