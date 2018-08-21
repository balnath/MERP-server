/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.bootstrap;

import com.merp.server.model.Role;
import com.merp.server.repository.RoleRepository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Divyanshu
 */
@Component
public class RoleBootstrap {

    @Inject
    RoleRepository roleRepository;

    private static final Logger logger = LogManager.getLogger();

    @PostConstruct
    public void initializeRoles() {
        String [] roleNames = {"DOCTOR", "RECEPTIONIST", "NURSE"};
        
        for(String roleName : roleNames) {
            try {
                Role role = roleRepository.findByName(roleName);
                if(role == null) {
                    logger.info("Adding Role : " + roleName);
                    role = new Role();
                    role.setName(roleName);
                    role.setDescription("Description for " + roleName);
                    roleRepository.save(role);
                    logger.info("Role " + roleName + " added to the database");
                } else {
                    logger.info("Role " + roleName + " already present in the database");
                }
            } catch (Exception e) {
                logger.error("Exception occured while adding Role for role name : " + roleName, e);
            }
        }
    }
}
