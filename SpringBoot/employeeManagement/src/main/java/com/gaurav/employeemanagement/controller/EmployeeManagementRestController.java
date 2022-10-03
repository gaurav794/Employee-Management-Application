package com.gaurav.employeemanagement.controller;

import com.gaurav.employeemanagement.exceptionHandler.RestResponseStatus;
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
//COMPLETED
public class EmployeeManagementRestController {
    private final EmployeeManagementService employeeManagementService;

    public EmployeeManagementRestController(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }

    //get user from the database
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity getUserRole(@Valid @RequestBody UserRole details) {
        boolean flag = false;
        try {
            flag = employeeManagementService.findUserRole(details);
        } catch (Exception e) {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(!flag ? failureMessage("Incorrect Credentials. Please try again.")
                : successMessage("Valid User"), HttpStatus.OK);
    }

    //add user to the database
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity addUserRole(@Valid @RequestBody UserRole details) {
        ResponseEntity flag = null;
        try {
            flag = employeeManagementService.saveUserRole(details);
        } catch (Exception e) {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(successMessage("Role added successfully."), HttpStatus.OK);
    }

    //get list of employees from the database
    @GetMapping("/getEmployees")
    public ResponseEntity getEmployeeList() {
        ResponseEntity flag = null;
        try {
            flag = employeeManagementService.getEmployees();
        } catch (Exception e) {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(flag.getBody(), flag.getStatusCode());
    }

    //add employee to the database
    @PostMapping("/addEmployee")
    @ResponseBody
    public ResponseEntity addEmployeeDetails(@Valid @RequestBody Employee employee) {
        ResponseEntity flag = null;
        try {
            flag = employeeManagementService.addEmployee(employee);
        } catch (Exception e) {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(successMessage("Employee added successfully."), HttpStatus.OK);
    }

    @GetMapping("/getPayrolls")
    @ResponseBody
    public ResponseEntity getEmployeePayrolls() {
        ResponseEntity flag = null;
        try {
            flag = employeeManagementService.getPayrolls();
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(flag.getBody(), flag.getStatusCode());
    }

    @PostMapping("/addPayroll")
    @ResponseBody
    public ResponseEntity addEmployeePayroll(@Valid @RequestBody Payroll payroll) {
        ResponseEntity flag = null;
        try {
            flag = employeeManagementService.addPayroll(payroll);
        } catch (Exception e) {
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
