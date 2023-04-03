package com.springboottesting1.testspringboot1.service;

import com.springboottesting1.testspringboot1.modal.Employee;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAlEmployee();

    Optional<Employee> getEmployeeById(long id);


}
