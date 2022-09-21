package com.gaurav.employeemanagement.repository;

import com.gaurav.employeemanagement.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserRole, Long>
{
}
