package com.gaurav.employeemanagement.repository;

import com.gaurav.employeemanagement.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserRole, Long>
{
    @Query("SELECT user from UserRole user where user.email_id = ?1")
    public UserRole findByEmail_id(String email_id);
}
