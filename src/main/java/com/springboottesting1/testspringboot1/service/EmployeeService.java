package com.springboottesting1.testspringboot1.service;

import com.springboottesting1.testspringboot1.modal.Employee;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAlEmployee();


}
