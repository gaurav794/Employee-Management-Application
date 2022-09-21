package com.gaurav.employeemanagement.service;

import com.gaurav.employeemanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeManagementService
{
    private final UserRepository userRepository;

    public EmployeeManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
