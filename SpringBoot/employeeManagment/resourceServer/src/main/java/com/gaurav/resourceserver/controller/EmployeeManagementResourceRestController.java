package com.gaurav.resourceserver.controller;

import com.gaurav.resourceserver.exceptionHandler.RestResponseStatus;
import com.gaurav.resourceserver.model.Employee;
import com.gaurav.resourceserver.model.Payroll;
import com.gaurav.resourceserver.model.UserRole;
import com.gaurav.resourceserver.service.EmployeeManagementResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 1800)
//COMPLETED
public class EmployeeManagementResourceRestController {
    private final EmployeeManagementResourceService employeeManagementResourceService;

    public EmployeeManagementResourceRestController(EmployeeManagementResourceService employeeManagementResourceService) {
        this.employeeManagementResourceService = employeeManagementResourceService;
    }

    //get user from the database
    @GetMapping("/login")
    public ResponseEntity getUserRole(@RequestParam(value="email_id") String email_id,@RequestParam(value = "password") String password) {
        boolean flag = false;
        UserRole details = new UserRole(email_id,password);
        try {
            flag = employeeManagementResourceService.findUserRole(details);
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
        return employeeManagementResourceService.saveUserRole(details);
    }

    //get list of employees from the database
    @GetMapping("/getEmployees")
    public ResponseEntity getEmployeeList() {
        return employeeManagementResourceService.getEmployees();
    }

    @GetMapping("/getEmployee")
    public ResponseEntity getEmployee(@RequestParam(value="pid") String pid) {
        return employeeManagementResourceService.getEmployeeByPid(pid);
    }

    //add employee to the database
    @PostMapping("/addEmployee")
    @ResponseBody
    public ResponseEntity addEmployeeDetails(@Valid @RequestBody Employee employee) {
        return employeeManagementResourceService.addEmployee(employee);
    }

    @GetMapping("/getPayrolls")
    @ResponseBody
    public ResponseEntity getEmployeePayrolls() {
        return employeeManagementResourceService.getPayrolls();
    }

    @PostMapping("/addPayroll")
    @ResponseBody
    public ResponseEntity addEmployeePayroll(@Valid @RequestBody Payroll payroll) {
        return employeeManagementResourceService.addPayroll(payroll);
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
