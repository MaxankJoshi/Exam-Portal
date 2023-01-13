package com.exam.controller;

import com.exam.entities.Role;
import com.exam.entities.User;
import com.exam.entities.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user/api")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create/{roleName}")
    public ResponseEntity<User> createUser(@RequestBody User user, @PathVariable("roleName") String roleName) throws Exception {
        user.setProfile("default.png");

        Role role = new Role();

        if(roleName.equals("NORMAL")) {
            role.setRoleId(2);
            role.setRoleName(roleName);
        }

        else {
            role.setRoleId(1);
            role.setRoleName(roleName);
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User createdUser = this.userService.createUser(user, userRoles);

        return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId) {
        User user = this.userService.getUser(userId);

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        this.userService.deleteUser(username);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
