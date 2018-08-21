/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.repository;

import com.merp.server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Divyanshu
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String roleName);
}
