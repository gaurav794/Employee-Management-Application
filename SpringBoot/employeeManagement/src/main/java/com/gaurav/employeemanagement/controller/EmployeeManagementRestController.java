package com.gaurav.employeemanagement.controller;

import com.gaurav.employeemanagement.model.Employee;
import com.gaurav.employeemanagement.model.Payroll;
import com.gaurav.employeemanagement.model.UserRole;
import com.gaurav.employeemanagement.service.EmployeeManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeManagementRestController
{
    private final EmployeeManagementService employeeManagementService;

    public EmployeeManagementRestController(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }
    //TODO: Develop endpoints to retrieve data from the database
    //get user from the database
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity getUserRole(@RequestBody UserRole details)
    {

        UserRole result = employeeManagementService.findUserRole(details);
        //user not found
        if(result == null)
            return new ResponseEntity(null,HttpStatus.OK);
        //success: true
        return new ResponseEntity(true,HttpStatus.OK);
    }
    //add user to the database
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity addUserRole(@RequestBody UserRole details)
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
    public ResponseEntity addEmployeeDetails(@RequestBody Employee employee)
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
    public ResponseEntity addEmployeePayroll(@RequestBody Payroll payroll)
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
