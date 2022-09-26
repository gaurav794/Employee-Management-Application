package com.gaurav.employeemanagement.repository;

import com.gaurav.employeemanagement.model.Employee;
import com.gaurav.employeemanagement.model.Payroll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends CrudRepository<Payroll, Long>
{
    @Query("SELECT payroll from Payroll payroll where payroll.employee.employee_id = ?1")
    Iterable<Payroll> findByEmployee_id(Long id);
}
