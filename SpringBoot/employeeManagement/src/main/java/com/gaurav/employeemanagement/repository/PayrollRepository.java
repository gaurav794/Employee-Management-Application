package com.gaurav.employeemanagement.repository;

import com.gaurav.employeemanagement.model.Payroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends CrudRepository<Payroll, Long>
{
}
