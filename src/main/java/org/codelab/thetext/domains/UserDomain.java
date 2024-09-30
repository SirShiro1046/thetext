package org.codelab.thetext.domains;

import org.codelab.thetext.dtos.request.UserRequestDTO;
import org.codelab.thetext.persistence.entities.User;

import java.util.List;

public interface UserDomain {
    User save(UserRequestDTO userRequest);
    List<User> findAllById(List<Long> userIds);
}
