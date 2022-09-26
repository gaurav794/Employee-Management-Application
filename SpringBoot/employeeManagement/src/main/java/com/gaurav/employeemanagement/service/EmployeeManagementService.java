package com.gaurav.employeemanagement.service;

import com.gaurav.employeemanagement.model.Employee;
import com.gaurav.employeemanagement.model.Payroll;
import com.gaurav.employeemanagement.model.UserRole;
import com.gaurav.employeemanagement.repository.EmployeeRepository;
import com.gaurav.employeemanagement.repository.PayrollRepository;
import com.gaurav.employeemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeManagementService
{
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;

    public EmployeeManagementService(UserRepository userRepository, EmployeeRepository employeeRepository, PayrollRepository payrollRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
    }

    //find user from the UserRole table
    public UserRole findUserRole(UserRole user)
    {
        UserRole result = null;
        try
        {
            result = userRepository.findByEmail_id(user.getEmail_id(),user.getPassword());
        }
        catch(Exception e)
        {
            result = null;
        }
        if(result == null)
            return null;

        return result;
    }
    //add user to UserRole table
    public ResponseEntity saveUserRole(UserRole user)
    {
        UserRole newUser = new UserRole(user.getUser_name(),user.getEmail_id(),user.getPhone_number(),user.getPassword(),new Date());
        try
        {
            userRepository.save(newUser);
        }
        catch(Exception e)
        {
           return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Role " + newUser.getUser_name() + " successfully added.", HttpStatus.OK);
    }

    //get employees from Employee table
    public ResponseEntity getEmployees()
    {
        List<Employee> listOfEmployee = null;
        try
        {
            listOfEmployee = employeeRepository.findAll();
        }
        catch(Exception e)
        {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(listOfEmployee, HttpStatus.OK);
    }

    //Add new employee to the Employee table
    public ResponseEntity addEmployee(Employee employee)
    {
        Employee newEmployee = new Employee(employee.getPid(), employee.getName(),employee.getAddress(),employee.getPhone_number(),employee.getDesignation(),employee.getDaily_wage(),new Date(),null,new Date());
        try
        {
            employeeRepository.save(newEmployee);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Employee " + newEmployee.getName() + " successfully added.", HttpStatus.OK);
    }

    //get payrolls from Payroll table
    public ResponseEntity getPayrolls(Employee employee)
    {
        List<Payroll> listOfPayroll = null;
        try
        {
            listOfPayroll = payrollRepository.findByEmployee_id(employee.getEmployee_id());
        }
        catch(Exception e)
        {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(listOfPayroll, HttpStatus.OK);
    }

    //Add new employee's payroll to the Payroll table
    public ResponseEntity addPayroll(Payroll p)
    {
        Payroll newPayroll = new Payroll(p.getPayroll_month(),p.getAttendance(),p.getDaily_wage(),p.getGenerated_salary(),p.getDeductions(),p.getNet_pay(),p.getPayment_mode(),new Date(),p.getEmployee());
        try
        {
            payrollRepository.save(newPayroll);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Payroll of " + newPayroll.getEmployee().getName() + " successfully generated.", HttpStatus.OK);
    }
}
