package com.gaurav.employeemanagement.service;

import com.gaurav.employeemanagement.model.UserRole;
import com.gaurav.employeemanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeManagementService
{
    private final UserRepository userRepository;

    public EmployeeManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserRolePresent(UserRole userRole)
    {
        UserRole result = userRepository.findByEmail_id(userRole.getEmail_id());
        System.out.println(result);
        return true;
    }
}
