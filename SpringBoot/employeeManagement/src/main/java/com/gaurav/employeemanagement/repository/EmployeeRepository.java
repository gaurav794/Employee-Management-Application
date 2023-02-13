package com.gaurav.employeemanagement.repository;

import com.gaurav.employeemanagement.model.Employee;
import com.gaurav.employeemanagement.model.UserRole;
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
    public Employee findByPid(String pid);
}
