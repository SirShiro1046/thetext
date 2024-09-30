package org.codelab.thetext.services;

import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.persistence.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);

    User save(User user);
}
