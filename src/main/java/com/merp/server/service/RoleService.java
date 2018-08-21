/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.service;

import com.merp.server.model.Role;
import java.util.List;

/**
 *
 * @author Divyanshu
 */
public interface RoleService {

    public List<Role> getAllRoles() throws Exception;

    public Role getRoleById(long roleId) throws Exception;

    public Role getRoleByName(String name) throws Exception;
    
}
