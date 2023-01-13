package com.exam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "firstname", nullable = false, length = 15)
    private String userFirstName;

    @Column(name = "lastname", nullable = false, length = 15)
    private String userLastName;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String userPassword;

    @Column(name = "email", nullable = false, length = 30, unique = true)
    private String userEmail;

    @Column(name = "phone", nullable = false, length = 15)
    private String userPhone;

    @Column(name = "enabled", nullable = false)
    private boolean userEnabled = true;

    @Column(name = "profile", nullable = false)
    private String profile;

    @Column(name = "about", nullable = false, length = 300)
    private String userAbout;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();

        for(UserRole userRole:userRoles) {
            authorities.add(new Authority(userRole.getRole().getRoleName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEnabled;
    }
}
