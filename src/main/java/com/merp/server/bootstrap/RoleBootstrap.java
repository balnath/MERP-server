/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.bootstrap;

import com.merp.server.model.Permission;
import com.merp.server.model.Role;
import com.merp.server.repository.PermissionRepository;
import com.merp.server.repository.RoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Inject
    PermissionRepository permissionRepository;

    private static final Logger logger = LogManager.getLogger();

    @PostConstruct
    public void initializeRoles() {
        // add permissions first
        initializePermissions();
        // Add role with permissions 
        String[] roleNames = {"DOCTOR", "RECEPTIONIST"};

        for (String roleName : roleNames) {
            try {
                Role role = roleRepository.findByName(roleName);
                if (role == null) {
                    Set<Permission> setOfPermissionsForAdmin = new HashSet<>(permissionRepository.findAll());
                    Set<Permission> permissionForDoctor = new HashSet<>();
                    Set<Permission> permissionForReceptionist = new HashSet<>();
                    for (Permission permission : setOfPermissionsForAdmin) {
                        if (permission.getName().equals("DASHBOARD")) {
                            permissionForDoctor.add(permission);
                            permissionForReceptionist.add(permission);
                        } else if (permission.getName().equals("PATIENT ALL")) {
                            permissionForReceptionist.add(permission);
                        }
                    }
                    logger.info("Adding Role : " + roleName);
                    role = new Role();
                    role.setName(roleName);
                    role.setDescription("Description for " + roleName);
                    if (role.getName().equalsIgnoreCase("DOCTOR")) {
                        role.setPermissions(permissionForDoctor);
                    } else if (role.getName().equalsIgnoreCase("RECEPTIONIST")) {
                        role.setPermissions(permissionForReceptionist);
                    }
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

    public void initializePermissions() {
        String[] permissionNames = {"PATIENT ALL", "ADMIN ALL", "DASHBOARD", "ADD USER", "ADD DEPARTMENT", "ADD PATIENT"};
        for (String permissionName : permissionNames) {
            try {
                Permission permission = permissionRepository.findByName(permissionName);
                if (permission == null) {
                    logger.info("Adding permission : " + permissionName);
                    Permission permission1 = new Permission();
                    permission1.setName(permissionName);
                    permission1.setDescription("Description for " + permissionName);
                    permissionRepository.save(permission1);
                    logger.info("Permission " + permissionName + " is added to database");
                } else {
                    logger.info("Permission " + permissionName + " is already present in database");
                }
            } catch (Exception e) {
                logger.error("Exception occured while adding permission for permission name : " + permissionName, e);
            }
        }
    }
}
