package com.project.panini.user;

import com.project.panini.util.UserContextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserContextService userContextService;

    public List<String> getAllUsernamesExceptSelf() {
        String username = this.userContextService.getUsername();
        return this.userRepository.getAllUsernamesExceptSelf(username);
    }
}
