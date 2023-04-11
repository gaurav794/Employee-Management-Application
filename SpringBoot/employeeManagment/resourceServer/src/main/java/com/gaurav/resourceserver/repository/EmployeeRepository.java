package com.gaurav.resourceserver.repository;

import com.gaurav.resourceserver.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>
{
    @Override
    List<Employee> findAll();

    @Query("SELECT emp from Employee emp where emp.pid = ?1")
    Employee findByPid(String pid);
}
