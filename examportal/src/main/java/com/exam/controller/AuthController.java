package com.exam.controller;

import com.exam.entities.JwtRequest;
import com.exam.entities.JwtResponse;
import com.exam.entities.User;
import com.exam.exception.UserNotFoundException;
import com.exam.security.CustomUserDetailsService;
import com.exam.security.JwtTokenHelper;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserService userService;

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        try {
            this.authenticationManager.authenticate(authenticationToken);
        }

        catch (DisabledException ex) {
            throw new Exception("USER DISABLED " + ex.getMessage());
        }

        catch (BadCredentialsException ex) {
            throw new Exception("Invalid username and password " + ex.getMessage());
        }
    }

    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }

        catch (Exception e) {
            e.printStackTrace();
            throw new UserNotFoundException();
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);

        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = ((User)this.customUserDetailsService.loadUserByUsername(principal.getName()));

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
