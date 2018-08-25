package com.merp.server.service;

import java.util.List;

import com.merp.server.model.User;

public interface UserService {
	
	public void saveUser(User user) throws Exception;
	
	public List<User> getUserList();
	
	public User getUserByName(String userName);
        
        public List<User> findUsersByRoleName(String roleName) throws Exception ;
        
        public User getUserById(long id) throws Exception ;
        
        public void removeUserById(long id) throws Exception ;

}
