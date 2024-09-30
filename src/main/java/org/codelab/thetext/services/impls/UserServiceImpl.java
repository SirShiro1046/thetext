package org.codelab.thetext.services.impls;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.dtos.request.UserRequestDTO;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.persistence.repositories.UserRepository;
import org.codelab.thetext.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Optional<User> findByUsername(String username) {
        log.info("Buscando usuario por nombre de usuario: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("Buscando usuario por id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllById(List<Long> userIds) {
        log.info("Buscando usuarios por ids: {}", userIds);
        return userRepository.findAllById(userIds);
    }


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
