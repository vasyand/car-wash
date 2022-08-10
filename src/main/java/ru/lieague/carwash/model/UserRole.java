package ru.lieague.carwash.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public enum UserRole {
    USER,
    ADMIN,
    OPERATOR;

    public Set<GrantedAuthority> getPermissions() {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}
