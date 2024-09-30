package org.codelab.thetext.services;

import org.codelab.thetext.dtos.request.UserRequestDTO;
import org.codelab.thetext.persistence.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findAllById(List<Long> userIds);
    User createUser(User user);
}
