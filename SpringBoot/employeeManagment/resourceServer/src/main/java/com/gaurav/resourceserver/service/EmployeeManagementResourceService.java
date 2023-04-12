package com.gaurav.resourceserver.service;

import com.gaurav.resourceserver.exceptionHandler.RestResponseStatus;
import com.gaurav.resourceserver.model.Employee;
import com.gaurav.resourceserver.model.Payroll;
import com.gaurav.resourceserver.model.UserRole;
import com.gaurav.resourceserver.repository.EmployeeRepository;
import com.gaurav.resourceserver.repository.PayrollRepository;
import com.gaurav.resourceserver.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeManagementResourceService
{
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;
//    private final PasswordEncoder bcryptPasswordEncoder;

//    public EmployeeManagementResourceService(UserRepository userRepository, EmployeeRepository employeeRepository, PayrollRepository payrollRepository, PasswordEncoder bcryptPasswordEncoder) {
//        this.userRepository = userRepository;
//        this.employeeRepository = employeeRepository;
//        this.payrollRepository = payrollRepository;
//        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
//    }


    public EmployeeManagementResourceService(UserRepository userRepository, EmployeeRepository employeeRepository, PayrollRepository payrollRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
    }

    //find user from the UserRole table
    public boolean findUserRole(UserRole user)
    {
        UserRole result = null;
        try
        {
            result = userRepository.findByEmail_id(user.getEmail_id());
        }
        catch(Exception e)
        {
            result = null;
        }

//        if(result != null && user.getEmail_id().equals(result.getEmail_id()) && bcryptPasswordEncoder.matches(user.getPassword(),result.getPassword()))
//            return true;
        if(result != null && user.getEmail_id().equals(result.getEmail_id()) && user.getPassword().equals(result.getPassword()))
            return true;

        return false;
    }
    //add user to UserRole table
    public ResponseEntity saveUserRole(UserRole user)
    {
        //Encoding the password
        String rawPassword = user.getPassword();
//        String encodedPassword = bcryptPasswordEncoder.encode(rawPassword);
//        UserRole newUser = new UserRole(user.getUser_name(),user.getEmail_id(),user.getPhone_number(),encodedPassword, new Date());
        UserRole newUser = new UserRole(user.getUser_name(),user.getEmail_id(),user.getPhone_number(),rawPassword, new Date());


        try
        {
            userRepository.save(newUser);
        }
        catch(DataIntegrityViolationException e)
        {
            return new ResponseEntity(failureMessage("User Role with email id already exists!"), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(successMessage("User Role added successfully."), HttpStatus.OK);

    }

    //get employees from Employee table
    public ResponseEntity getEmployees()
    {
        List<Employee> listOfEmployee = null;
        try
        {
            listOfEmployee = employeeRepository.findAll();
            if(listOfEmployee == null)
                return new ResponseEntity(successMessage("NO RECORDS FOUND"), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(listOfEmployee, HttpStatus.OK);
    }

    //get employee by pid from Employee table
    public ResponseEntity getEmployeeByPid(String pid)
    {
        Employee employee = null;
        try
        {
            employee = employeeRepository.findByPid(pid);
            if(employee == null)
                return new ResponseEntity(successMessage("NO RECORDS FOUND"), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(employee, HttpStatus.OK);
    }

    //Add new employee to the Employee table
    public ResponseEntity addEmployee(Employee employee)
    {
        Employee newEmployee = new Employee(employee.getPid(), employee.getName(),employee.getAddress(),employee.getPhone_number(),employee.getDesignation(),employee.getDaily_wage(),employee.getDoj(),null,new Date());
        try
        {
            employeeRepository.save(newEmployee);
        }
        catch(DataIntegrityViolationException e)
        {
            return new ResponseEntity(failureMessage("Employee with PID already exists! Please check entered details."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(successMessage("Employee added successfully."), HttpStatus.OK);
    }

    //get payrolls from Payroll table
    public ResponseEntity getPayrolls()
    {
        List<Payroll> listOfPayroll = null;
        try
        {
            listOfPayroll = payrollRepository.findAll();
            if(listOfPayroll == null)
                return new ResponseEntity(successMessage("NO RECORDS FOUND"), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(listOfPayroll, HttpStatus.OK);
    }

    //Add new employee's payroll to the Payroll table
    public ResponseEntity addPayroll(Payroll p)
    {
        Payroll newPayroll = new Payroll(p.getPayroll_month(),p.getPayroll_year(),p.getAttendance(),p.getDaily_wage(),p.getGenerated_salary(),p.getDeductions(),p.getNet_pay(),p.getPayment_mode(),p.getDate_added(),p.getEmployee());
        try
        {
            //find duplicate payroll in the database if any
            int month = newPayroll.getPayroll_month();
            int year = newPayroll.getPayroll_year();
            Long employee_id = newPayroll.getEmployee().getEmployee_id();
            Payroll result = payrollRepository.findPayrollByMonthYearAndEmployee_id(month,year,employee_id);
            if(result != null)
                return new ResponseEntity(failureMessage("Payroll already exists!"), HttpStatus.INTERNAL_SERVER_ERROR);

            payrollRepository.save(newPayroll);
        }
        catch(Exception e)
        {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(successMessage("Payroll generated successfully."), HttpStatus.OK);
    }

    private RestResponseStatus successMessage(String message) {
        return new RestResponseStatus("SUCCESS", message);
    }

    private RestResponseStatus failureMessage(String message) {
        return new RestResponseStatus("FAILURE", message);
    }

    private RestResponseStatus errorMessage() {
        return new RestResponseStatus("INTERNAL_SERVER_ERROR",
                "Internal server error, please try again after sometime. If this problem continues, contact IT Department.");
    }
}
