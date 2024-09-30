package org.codelab.thetext.services.impls;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.persistence.repository.UserRepository;
import org.codelab.thetext.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Log4j2
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
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
    public User save(User user) {
        log.info("Guardando usuario: {}", user);
        return userRepository.save(user);
    }
}
