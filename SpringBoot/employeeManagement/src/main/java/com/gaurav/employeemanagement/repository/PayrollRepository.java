package com.gaurav.employeemanagement.repository;

import com.gaurav.employeemanagement.model.Employee;
import com.gaurav.employeemanagement.model.Payroll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends CrudRepository<Payroll, Long>
{
    @Override
    List<Payroll> findAll();
    @Query("SELECT payroll FROM Payroll payroll WHERE payroll.employee.employee_id = ?1")
    List<Payroll> findByEmployee_id(Long id);
}
