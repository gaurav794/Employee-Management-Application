package com.gaurav.resourceserver.repository;

import com.gaurav.resourceserver.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserRole, Long>
{
    @Query("SELECT user from UserRole user where user.email_id = ?1")
    public UserRole findByEmail_id(String email_id);
    @Query("SELECT user from UserRole user where user.email_id = ?1 and user.password = ?2")
    public UserRole findByEmail_idAndPassword(String email_id,String password);
}
