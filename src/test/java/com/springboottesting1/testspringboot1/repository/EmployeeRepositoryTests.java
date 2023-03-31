package com.springboottesting1.testspringboot1.repository;

import com.springboottesting1.testspringboot1.Repository.EmployeeRepository;
import com.springboottesting1.testspringboot1.modal.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @DisplayName("Junit test for save employee operations")
    @Test
    public void giveEmployeeObject_whenSave_thenReturnSavedEmployee(){

        Employee employee = Employee.builder()
                            .firstName("Anandhu")
                            .lastName("Reji")
                            .email("anandhureji33@gmail.com")
                            .build();

        Employee savedEmployee = employeeRepository.save(employee);

        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    @DisplayName("Junit test for getAll ")
    @Test
    public void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){

        Employee employee1 = Employee.builder()
                .firstName("Anandhu")
                .lastName("Reji")
                .email("anandhureji33@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Arjun")
                .lastName("Reji")
                .email("arjun@gmail.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        List<Employee> employeeList = employeeRepository.findAll();

        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);


    }

    @Test
    @DisplayName("Junit test cases for findByEmail_id")
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeEmail(){

        Employee employee1 = Employee.builder()
                .firstName("Anandhu")
                .lastName("Reji")
                .email("anandhureji33@gmail.com")
                .build();

        employeeRepository.save(employee1);

        Employee empEmail = employeeRepository.findByEmail(employee1.getEmail()).get();

        Assertions.assertThat(empEmail).isNotNull();
    }




}
