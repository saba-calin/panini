package com.project.panini.util;

import com.project.panini.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserContextService {

    public Long getUserId() {
        return getPrincipal().getId();
    }

    public String getUsername() {
        return getPrincipal().getUsername();
    }

    public User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
