/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.serviceImpl;

import com.merp.server.model.Role;
import com.merp.server.repository.RoleRepository;
import com.merp.server.service.RoleService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Divyanshu
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    
    @Inject
    RoleRepository roleRepository;

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public List<Role> getAllRoles() throws Exception{
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            logger.error("Exception occured while fetching all roles !", e);
            throw new Exception("Exception occured while fetching all roles !");
        }
    }

    @Override
    public Role getRoleById(long roleId) throws Exception{
        try {
            return roleRepository.findById(roleId).get();
        } catch (Exception e) {
            logger.error("Exception occured while fetching role with id : " + roleId, e);
            throw new Exception("Exception occured while fetching role with id : " + roleId);
        }
    }

    @Override
    public Role getRoleByName(String name) throws Exception {
        try {
            return roleRepository.findByName(name);
        } catch (Exception e) {
            logger.error("Exception occured while fetching role with name : " + name, e);
            throw new Exception("Exception occured while fetching role with name : " + name);
        }
    }
    
}
