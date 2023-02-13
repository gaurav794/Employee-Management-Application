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
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 1800)
//COMPLETED
public class EmployeeManagementRestController {
    private final EmployeeManagementService employeeManagementService;

    public EmployeeManagementRestController(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }

    //get user from the database
    @GetMapping("/login")
    public ResponseEntity getUserRole(@RequestParam(value="email_id") String email_id,@RequestParam(value = "password") String password) {
        boolean flag = false;
        UserRole details = new UserRole(email_id,password);
        try {
            flag = employeeManagementService.findUserRole(details);
        } catch (Exception e) {
            return new ResponseEntity(errorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(!flag ? failureMessage("Incorrect Credentials. Please try again.")
                : successMessage("Valid User"), HttpStatus.OK);
    }

    //add user role to the database
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity addUserRole(@Valid @RequestBody UserRole details) {
        return employeeManagementService.saveUserRole(details);
    }

    //get list of employees from the database
    @GetMapping("/getEmployees")
    public ResponseEntity getEmployeeList() {
        return employeeManagementService.getEmployees();
    }

    @GetMapping("/getEmployee")
    public ResponseEntity getEmployee(@RequestParam(value="pid") String pid) {
        return employeeManagementService.getEmployeeByPid(pid);
    }

    //add employee to the database
    @PostMapping("/addEmployee")
    @ResponseBody
    public ResponseEntity addEmployeeDetails(@Valid @RequestBody Employee employee) {
        return employeeManagementService.addEmployee(employee);
    }

    @GetMapping("/getPayrolls")
    @ResponseBody
    public ResponseEntity getEmployeePayrolls() {
        return employeeManagementService.getPayrolls();
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
