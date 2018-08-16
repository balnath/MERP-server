/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merp.server.controller;

import com.merp.server.model.User;
import com.merp.server.repository.UserRepository;
import com.merp.server.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            User foundUser = userRepository.findByUsername(user.getUsername());
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody User user) throws Exception {
		User user2 = userService.getUserByName(user.getUsername());
		try {
			if (user2 != null && authenticate(user, user2)) {
				return new ResponseEntity<>("User logged in Successfully !",  HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		return new ResponseEntity<>("Please enter correct credentials", HttpStatus.BAD_REQUEST);

	}

	private boolean authenticate(User formUser, User dbUser) {
		if (formUser.getUsername().equals(dbUser.getUsername())
				&& formUser.getPassword().equals(dbUser.getPassword())) {
			return true;
		} else {
			return false;
		}

	}

}
