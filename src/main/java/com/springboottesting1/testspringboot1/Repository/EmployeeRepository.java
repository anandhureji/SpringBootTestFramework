package com.springboottesting1.testspringboot1.Repository;

import com.springboottesting1.testspringboot1.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
