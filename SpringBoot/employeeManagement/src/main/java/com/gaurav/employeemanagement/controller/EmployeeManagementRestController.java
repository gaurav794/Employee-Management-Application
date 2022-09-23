package com.gaurav.employeemanagement.controller;

import com.gaurav.employeemanagement.model.UserRole;
import com.gaurav.employeemanagement.service.EmployeeManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    //TODO: Check User if it already exists, get information about
    // @Column(unique) constraint
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
}
