// src/main/java/org/codelab/thetext/services/impl/UserDetailsServiceImpl.java
package org.codelab.thetext.services.impl;

import lombok.AllArgsConstructor;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.persistence.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
    builder.password(user.getPassword());
    builder.roles(user.getRoles().toArray(new String[0])); // Ensure roles are correctly set
    return builder.build();
}
}