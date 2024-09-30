package org.codelab.thetext.domains;

import org.codelab.thetext.persistence.entities.Friendship;
import org.codelab.thetext.utilities.FriendshipStatus;

import java.util.List;

public interface FriendshipDomain {
    Friendship sendFriendRequest(Long userId, Long friendId);
    Friendship acceptFriendRequest(Long friendshipId);
    void blockUser(Long userId, Long friendId);
    void removeFriend(Long userId, Long friendId);
    List<Friendship> findAllFriends(Long userId);
    FriendshipStatus getFriendshipStatus(Long userId, Long friendId);
}
