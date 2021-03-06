/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.controller;

import com.merp.server.model.User;
import com.merp.server.repository.UserRepository;
import com.merp.server.service.UserService;
import java.util.List;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Divyanshu
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LogManager.getLogger();

    public UserController(UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            User foundUser = userRepository.getUserByUsername(user.getUsername());
            if (foundUser == null) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return new ResponseEntity<>("User added successfully !", HttpStatus.OK);
            } else {
                logger.info("User already exists with the provided username : " + user.getUsername());
                return new ResponseEntity<>("User already exists with the provided username !", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            logger.error("An error occured while adding user !", e);
            return new ResponseEntity<>("An error occured while adding user !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-users-by-role")
    public ResponseEntity<List<User>> getUsersByRoleName(@RequestParam String roleName) {
        try {
            List<User> users = userService.findUsersByRoleName(roleName);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching users by role : " + roleName, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = userService.getUserList();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching all users", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-user-by-id")
    public ResponseEntity<User> getUserById(@RequestParam long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching all user with id : " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update-user")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
        try {
            User foundUser = userService.getUserById(user.getId());
            foundUser.setName(user.getName());
            foundUser.setEmail(user.getEmail());
            foundUser.setMobileNo(user.getMobileNo());
            foundUser.setDateOfBirth(user.getDateOfBirth());
            foundUser.setDateOfJoining(user.getDateOfJoining());
            foundUser.setRole(user.getRole());
            foundUser.setDepartment(user.getDepartment());
            userService.saveUser(user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while updating user with id : " + user.getId(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/remove-user") 
    public ResponseEntity<String> removeUser(@RequestParam long id) {
        try {
            userService.removeUserById(id);
            return new ResponseEntity<>("User removed successfully ", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while removing user with id : " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get-user-details-by-username")
    public ResponseEntity<User> getUserDetailsByUsername(@RequestParam String username) {
        try {
            User user = userService.getUserByName(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occured while fetching user details with username : " + username, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
