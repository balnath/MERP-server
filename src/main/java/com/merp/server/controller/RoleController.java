/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.controller;

import com.merp.server.model.Role;
import com.merp.server.service.RoleService;
import java.util.List;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Divyanshu
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
    
    @Inject
    RoleService roleService;
    
    private static final Logger logger = LogManager.getLogger();
    
    @GetMapping("/get-all-roles")
    public ResponseEntity<List<Role>> getAllRoles(){
         try {
            return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error occured while retreiving all roles !!", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get-role-by-name")
    public ResponseEntity<Role> getRoleByName(@RequestParam String roleName) {
        try {
            return new ResponseEntity<>(roleService.getRoleByName(roleName), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error occured while retreiving role with name : " + roleName, exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
    
    @GetMapping("/get-role-by-id")
    public ResponseEntity<Role> getRoleById(@RequestParam long roleId) {
        try {
            return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error("Error occured while retreiving role with id : " + roleId, exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
