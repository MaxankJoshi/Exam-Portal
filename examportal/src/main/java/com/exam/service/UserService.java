package com.exam.service;

import com.exam.entities.User;
import com.exam.entities.UserRole;

import java.util.Set;

public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(Integer userId);
    public void deleteUser(String username);
}
