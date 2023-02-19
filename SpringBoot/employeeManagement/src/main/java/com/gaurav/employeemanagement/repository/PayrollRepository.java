package com.gaurav.employeemanagement.repository;

import com.gaurav.employeemanagement.model.Employee;
import com.gaurav.employeemanagement.model.Payroll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PayrollRepository extends CrudRepository<Payroll, Long>
{
    @Override
    List<Payroll> findAll();

@Query("SELECT p from Payroll p where p.payroll_month = ?1 and p.date_added like ?2% and p.employee.employee_id =?3")
public Payroll findPayrollByPayroll_monthAndDate_addedAndEmployee_id(int month, String date,Long employee_id);
}
