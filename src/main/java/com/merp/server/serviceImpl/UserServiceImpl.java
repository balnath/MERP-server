package com.merp.server.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.merp.server.model.User;
import com.merp.server.repository.UserRepository;
import com.merp.server.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void saveUser(User user) throws Exception {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error occured while saving user", e);
            throw new Exception("Error occured while saving user");
        }

    }

    @Override
    public List<User> getUserList() {
        // TODO Auto-generated method stub
        return userRepository.findAllActiveUsers();
    }

    @Override
    public User getUserByName(String userName) {
        // TODO Auto-generated method stub
        User user = userRepository.getUserByUsername(userName);
        return user;
    }

    @Override
    public List<User> findUsersByRoleName(String roleName) throws Exception {
        try {
            return userRepository.findUsersByRoleName(roleName);
        } catch (Exception e) {
            logger.error("Could not retrieve Users by role name : " + roleName, e);
            throw new Exception("Could not retrieve Users by role name : " + roleName);
        }
    }

    @Override
    public User getUserById(long id) throws Exception {
        try {
            User user = userRepository.getUserById(id);
            return user;
        } catch (Exception e) {
            logger.error("Could not retrieve User by id : " + id, e);
            throw new Exception("Could not retrieve User by id : " + id, e);
        }
    }

    @Override
    public void removeUserById(long id) throws Exception {
        try {
            User user = userRepository.getUserById(id);
            user.setActive(false);
            userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error occured while deleting user with id : " + id, e);
            throw new Exception("Error occured while deleting user with id : " + id);
        }
    }

}
