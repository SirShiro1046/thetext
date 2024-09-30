package org.codelab.thetext.domains.impls;

import lombok.AllArgsConstructor;
import org.codelab.thetext.domains.FriendshipDomain;
import org.codelab.thetext.persistence.entities.Friendship;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.services.FriendshipService;
import org.codelab.thetext.services.UserService;
import org.codelab.thetext.utilities.FriendshipStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FriendshipDomainImpl implements FriendshipDomain {
    private final FriendshipService friendshipService;
    private final UserService userService;

    @Override
    public Friendship sendFriendRequest(Long userId, Long friendId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userService.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        //Verificar si ya existe una amistad
        Optional<Friendship> existingFriendship = friendshipService.findByUserAndFriend(user, friend);
        if (existingFriendship.isPresent()) {
            throw new RuntimeException("Friendship already exists");
        }

        Friendship friendship = Friendship.builder()
                .user(user)
                .friend(friend)
                .status(FriendshipStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return friendshipService.save(friendship);

    }

    @Override
    public Friendship acceptFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipService.findById(friendshipId);

        // Cambiar el estado a "ACCEPTED"
        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendship.setUpdatedAt(LocalDateTime.now());
        return friendshipService.save(friendship);
    }

    @Override
    public void blockUser(Long userId, Long friendId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userService.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        // Verificar si existe una amistad
        Optional<Friendship> existingFriendship = friendshipService.findByUserAndFriend(user, friend);
        if (existingFriendship.isPresent()) {
            Friendship friendship = existingFriendship.get();
            friendship.setStatus(FriendshipStatus.BLOCKED);
            friendship.setUpdatedAt(LocalDateTime.now());
            friendshipService.save(friendship);
        }else {
            throw new RuntimeException("Friendship not found");
        }

    }

    @Override
    public void removeFriend(Long userId, Long friendId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userService.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        Optional<Friendship> friendship = friendshipService.findByUserAndFriend(user, friend);
        friendship.ifPresent(f -> friendshipService.delete(f.getId()));
    }

    @Override
    public List<Friendship> findAllFriends(Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return friendshipService.findByUserOrFriend(user)
                .stream()
                .filter(friendship -> friendship.getStatus() == FriendshipStatus.ACCEPTED)
                .collect(Collectors.toList());
    }

    @Override
    public FriendshipStatus getFriendshipStatus(Long userId, Long friendId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userService.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));
        Optional<Friendship> friendship = friendshipService.findByUserAndFriend(user, friend);
        return friendship.map(Friendship::getStatus).orElse(FriendshipStatus.NONE);
    }
}
