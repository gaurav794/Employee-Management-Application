package com.gaurav.employeemanagement.controller;

import com.gaurav.employeemanagement.model.Employee;
import com.gaurav.employeemanagement.model.Payroll;
import com.gaurav.employeemanagement.model.UserRole;
import com.gaurav.employeemanagement.service.EmployeeManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class EmployeeManagementRestController
{
    private final EmployeeManagementService employeeManagementService;
    private final Error error;
    public EmployeeManagementRestController(EmployeeManagementService employeeManagementService, Error error) {
        this.employeeManagementService = employeeManagementService;
        this.error = error;
    }
    //TODO: Develop endpoints to retrieve data from the database
    //get user from the database
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity getUserRole(@Valid @RequestBody UserRole details)
    {
        UserRole result = null;
        try
        {
            result = employeeManagementService.findUserRole(details);
        }
        catch(Exception e)
        {
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //success: true
        return new ResponseEntity(result,HttpStatus.OK);
    }
    //add user to the database
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity addUserRole(@Valid @RequestBody UserRole details)
    {
        ResponseEntity flag = null;
        try
        {
            flag = employeeManagementService.saveUserRole(details);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(flag.getBody(),flag.getStatusCode());
    }
    //get list of employees from the database
    @GetMapping("/getEmployees")
    public ResponseEntity getEmployeeList()
    {
        ResponseEntity flag = null;
        try
        {
            flag = employeeManagementService.getEmployees();
        }
        catch(Exception e)
        {
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(flag.getBody(),flag.getStatusCode());
    }
    //add employee to the database
    @PostMapping("/addEmployee")
    @ResponseBody
    public ResponseEntity addEmployeeDetails(@Valid @RequestBody Employee employee)
    {
        ResponseEntity flag = null;
        try
        {
            flag = employeeManagementService.addEmployee(employee);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(flag.getBody(),flag.getStatusCode());
    }

    @GetMapping("/getPayrolls")
    @ResponseBody
    public ResponseEntity getEmployeePayrolls()
    {
        ResponseEntity flag = null;
        try
        {
            flag = employeeManagementService.getPayrolls();
        }
        catch(Exception e)
        {
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(flag.getBody(),flag.getStatusCode());
    }

    @PostMapping("/addPayroll")
    @ResponseBody
    public ResponseEntity addEmployeePayroll(@Valid @RequestBody Payroll payroll)
    {
        ResponseEntity flag = null;
        try
        {
            flag = employeeManagementService.addPayroll(payroll);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(flag.getBody(),flag.getStatusCode());
    }

}
