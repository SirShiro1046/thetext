package org.codelab.thetext.persistence.repository;

import org.codelab.thetext.persistence.entities.Friendship;
import org.codelab.thetext.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    //findByUserOrFriend
    List<Friendship> findByUserOrFriend(User user, User friend);
    List<Friendship> findAllByUserOrFriend(User user, User friend);
    Optional<Friendship> findByUserAndFriend(User user, User friend);
    boolean existsByUserAndFriend(User user, User friend);
    void deleteByUserAndFriend(User user, User friend);

}
