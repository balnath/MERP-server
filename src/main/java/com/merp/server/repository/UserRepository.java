/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.repository;

import com.merp.server.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Divyanshu
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
//    User findByUsername(String username);
    
    @Query(value = "Select u from User u where u.username = :username and u.isActive = true")
    User getUserByUsername(@Param("username") String username);
    
    @Query(value = "Select u from User u where u.id = :userId and u.isActive = true")
    User getUserById(@Param("userId") long id);
    
    @Query(value = "Select u from User u where u.isActive = true")
    List<User> findAllActiveUsers();
    
    @Query(value = "Select u from User u where u.role.name = :roleName and u.isActive = true")
    List<User> findUsersByRoleName(@Param("roleName") String roleName);
    
    
}
