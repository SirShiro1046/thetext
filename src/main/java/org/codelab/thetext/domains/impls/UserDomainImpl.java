package org.codelab.thetext.domains.impls;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.domains.UserDomain;
import org.codelab.thetext.dtos.UserRequestDTO;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.services.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class UserDomainImpl implements UserDomain {
    private final UserService userService;
    @Override
    public User save(UserRequestDTO userRequest) {
        log.info("Guardando usuario: {}", userRequest);
        User user = User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .description(userRequest.getDescription())
                .password(userRequest.getPassword())
                .build();
        return userService.save(user);
    }
}
