package org.codelab.thetext.services;

import org.codelab.thetext.persistence.entities.Friendship;
import org.codelab.thetext.persistence.entities.User;

import java.util.List;
import java.util.Optional;

public interface FriendshipService {
    Friendship save(Friendship friendship);
    Friendship findById(Long id);
    void delete(Long id);
    List<Friendship> findByUserOrFriend(User user);
    Optional<Friendship> findByUserAndFriend(User user, User friend);

}
