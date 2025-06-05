package com.project_technique.project_technique.security;

import com.project_technique.project_technique.models.Employe;
import com.project_technique.project_technique.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {


    private final Employe employe;

    public CustomUserDetails(Employe employe) {
        this.employe = employe;
    }

    public Long getId() {
        return employe.getId();
    }

    public String getFirstName() {
        return employe.getFirstName();
    }

    public  String getLastName(){
        return employe.getLastName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + employe.getRole().name()));
    }

    @Override
    public String getPassword() {
        return employe.getPassword();
    }

    @Override
    public String getUsername() {
        return employe.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }


}




