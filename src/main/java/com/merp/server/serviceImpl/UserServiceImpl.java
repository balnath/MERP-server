package com.merp.server.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.merp.server.model.User;
import com.merp.server.repository.UserRepository;
import com.merp.server.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Inject
	UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		User  user =  userRepository.findByUsername(userName);
		return user;
	}

}