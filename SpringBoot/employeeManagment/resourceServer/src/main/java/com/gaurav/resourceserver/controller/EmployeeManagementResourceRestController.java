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
//COMPLETED
public class EmployeeManagementResourceRestController {
    private final EmployeeManagementResourceService employeeManagementResourceService;

    public EmployeeManagementResourceRestController(EmployeeManagementResourceService employeeManagementResourceService) {
        this.employeeManagementResourceService = employeeManagementResourceService;
    }

    //get list of employees from the database
    @GetMapping("/getEmployees")
    public ResponseEntity getEmployeeList() {
        return employeeManagementResourceService.getEmployees();
    }

    @GetMapping("/getEmployee")
    public ResponseEntity getEmployee(@RequestParam(value = "pid") String pid) {
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
}
