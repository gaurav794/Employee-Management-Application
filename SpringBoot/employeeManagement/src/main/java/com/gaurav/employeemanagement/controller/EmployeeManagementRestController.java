package com.gaurav.employeemanagement.controller;

import com.gaurav.employeemanagement.model.UserRole;
import com.gaurav.employeemanagement.service.EmployeeManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class EmployeeManagementRestController
{
    private final EmployeeManagementService employeeManagementService;

    public EmployeeManagementRestController(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }
    //TODO: Develop endpoints to retrieve data from the database
    //TODO: Develop Model for LoginForm
    @GetMapping("/userRole")
    public ResponseEntity<UserRole> getUserRole(@RequestBody UserRole userRole)
    {
        boolean result = employeeManagementService.isUserRolePresent(userRole);
        return ResponseEntity.ok(userRole);
    }
}
