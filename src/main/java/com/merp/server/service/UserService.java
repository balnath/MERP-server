package com.merp.server.service;

import java.util.List;

import com.merp.server.model.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public List<User> getUserList();
	
	public User getUserByName(String userName);

}
