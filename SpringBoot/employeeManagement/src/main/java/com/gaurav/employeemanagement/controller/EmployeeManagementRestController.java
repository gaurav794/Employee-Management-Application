package com.gaurav.employeemanagement.controller;

import com.gaurav.employeemanagement.service.EmployeeManagementService;
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
}
