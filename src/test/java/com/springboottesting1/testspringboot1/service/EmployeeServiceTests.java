package com.springboottesting1.testspringboot1.service;

import com.springboottesting1.testspringboot1.Repository.EmployeeRepository;
import com.springboottesting1.testspringboot1.modal.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup(){

        //employeeRepository= Mockito.mock(EmployeeRepository.class);
        //employeeService= new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenEmployeeObject(){
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Anandhu")
                .lastName("Reji")
                .email("anandhureji33@gmail.com")
                .build();

        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee))
                .willReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        Assertions.assertThat(savedEmployee).isNotNull();



    }
}
