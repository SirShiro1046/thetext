package org.codelab.thetext.domains;

import org.codelab.thetext.dtos.request.UserRequestDTO;
import org.codelab.thetext.persistence.entities.User;

public interface UserDomain {
    User save(UserRequestDTO userRequest);
}
