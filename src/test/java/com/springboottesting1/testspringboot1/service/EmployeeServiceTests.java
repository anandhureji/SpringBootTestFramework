package com.springboottesting1.testspringboot1.service;

import com.springboottesting1.testspringboot1.Repository.EmployeeRepository;
import com.springboottesting1.testspringboot1.exception.ResourceNotFoundException;
import com.springboottesting1.testspringboot1.modal.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){

        employee = Employee.builder()
                .id(1L)
                .firstName("Anandhu")
                .lastName("Reji")
                .email("anandhureji33@gmail.com")
                .build();

        //employeeRepository= Mockito.mock(EmployeeRepository.class);
        //employeeService= new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenEmployeeObject(){


        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());
        given(employeeRepository.save(employee))
                .willReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        Assertions.assertThat(savedEmployee).isNotNull();



    }

    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){


        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));


        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class,()->{
            employeeService.saveEmployee(employee);

        });

        verify(employeeRepository,never()).save(any(Employee.class));
    }


    @DisplayName("Junit test cases for getAllEMployees")
    @Test
    public void givenEmployeesList_whenGetAllEMployees_thenReturnEMployeeList(){

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Arjun")
                .lastName("Reji")
                .email("arjun@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        List<Employee> employeeList = employeeService.getAlEmployee();

        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);

    }

    @DisplayName("Junit test cases for getAllEMployees with negative scenarios")
    @Test
    public void givenEmployeesList_whenGetAllEMployees_thenReturnEmptyEmployeeList(){

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Arjun")
                .lastName("Reji")
                .email("arjun@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        List<Employee> employeeList = employeeService.getAlEmployee();

        Assertions.assertThat(employeeList).isEmpty();
        Assertions.assertThat(employeeList.size()).isEqualTo(0);

    }

    @DisplayName("Junit test cases for findEmployeeById using mockito")
    @Test
    public void givenEmployeeID_whenFindById_thenReturnEmployee(){

        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        Optional<Employee> employee1 = Optional.of(employeeService.getEmployeeById(employee.getId()).get());

        Assertions.assertThat(employee1).isNotNull();



    }


    @DisplayName("Junit test cases for UpdateEmployee Method")
    @Test
    public void givenEmployee_whenUpdatesEmployee_thenReturnUpdatedEmployee(){
        //given
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("anandhureji33@gmail.com");
        employee.setLastName("Reji k");

        //when
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        //then
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("anandhureji33@gmail.com");
        Assertions.assertThat(updatedEmployee.getLastName()).isEqualTo("Reji k");


    }

    @DisplayName("Junit test cases for deleteByID")
    @Test
    public void givenEmployeeObject_whenDeleteEmployee_thenReturnVoid(){
        //given
        willDoNothing().given(employeeRepository).deleteById(1L);  //willDoNothing() to stub the meathod wich return void

        //when
        employeeService.deleteEmployee(1L);

        //then
        verify(employeeRepository,times(1)).deleteById(1L);





    }
}
