package com.gaurav.employeemanagement.service;

import com.gaurav.employeemanagement.model.UserRole;
import com.gaurav.employeemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeManagementService
{
    private final UserRepository userRepository;

    public EmployeeManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //find user from the UserRole table
    public UserRole findUserRole(UserRole user)
    {
        UserRole result = userRepository.findByEmail_id(user.getEmail_id());
        if(result == null)
            return null;
        return result;
    }
    //save user to UserRole table
    public ResponseEntity saveUserRole(UserRole user)
    {
        UserRole newUser = new UserRole(user.getUser_name(),user.getEmail_id(),user.getPhone_number(),user.getPassword(),new Date());
        UserRole savedUser = null;
        try
        {
            savedUser = userRepository.save(newUser);
        }
        catch(Exception e)
        {
           return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }
}
