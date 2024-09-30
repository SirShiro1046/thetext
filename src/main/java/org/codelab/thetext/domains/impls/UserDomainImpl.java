package org.codelab.thetext.domains.impls;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.domains.UserDomain;
import org.codelab.thetext.dtos.request.UserRequestDTO;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class UserDomainImpl implements UserDomain {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User save(UserRequestDTO userRequest) {
        log.info("Guardando usuario: {}", userRequest);
        User user = User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .description(userRequest.getDescription())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        return userService.createUser(user);
    }

    @Override
    public List<User> findAllById(List<Long> userIds) {
        log.info("Buscando usuarios por ids: {}", userIds);
        return userService.findAllById(userIds);
    }
}
