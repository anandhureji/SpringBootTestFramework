package com.springboottesting1.testspringboot1.Repository;

import com.springboottesting1.testspringboot1.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);

    @Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
    Employee findByJPQL(String firstName,String lastName);

    @Query("select e from Employee e where e.firstName=:firstName and e.lastName=:lastName")
    Employee findByJPQLNamedParameter(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
