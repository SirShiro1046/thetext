package org.codelab.thetext.services.impls;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.persistence.entities.Friendship;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.persistence.repository.FriendshipRepository;
import org.codelab.thetext.services.FriendshipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class FriendshipBaseServiceImpl implements FriendshipService{
    private final FriendshipRepository friendshipRepository;

    @Override
    public Friendship save(Friendship friendship) {
        log.info("Guardando Friendship: {}", friendship);
        return friendshipRepository.save(friendship);
    }

    @Override
    public Friendship findById(Long id) {
        log.info("Buscando Friendship por id: {}", id);
        return friendshipRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        log.info("Borrando Friendship: {}", id);
        friendshipRepository.deleteById(id);
    }

    @Override
    public List<Friendship> findByUserOrFriend(User user) {
        log.info("Buscando Friendship por usuario o amigo: {}", user);
        return friendshipRepository.findByUserOrFriend(user, user);
//        return friendshipService.findByUserOrFriend(user);
    }

    @Override
    public Optional<Friendship> findByUserAndFriend(User user, User friend) {
        log.info("Buscando Friendship por usuario y amigo: {} {}", user, friend);
        return friendshipRepository.findByUserAndFriend(user, friend);
    }
}
