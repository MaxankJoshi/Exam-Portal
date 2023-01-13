package com.exam.service.implementation;

import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.exception.UserFoundException;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = this.userRepository.findByUsername(user.getUsername());

        if(localUser != null) {
            System.out.println("User is already there");
            throw new UserFoundException();
        }

        else {
            for(UserRole userRole:userRoles) {
                this.roleRepository.save(userRole.getRole());
            }

            user.setUserRoles(userRoles);
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            localUser = this.userRepository.save(user);
        }

        return localUser;
    }

    @Override
    public User getUser(Integer userId) {
        return this.userRepository.findById(userId).get();
    }

    @Override
    public void deleteUser(String username) {
        User user = this.userRepository.findByUsername(username);

        this.userRepository.delete(user);
    }
}
