package org.codelab.thetext.controllers;


import lombok.AllArgsConstructor;
import org.codelab.thetext.domains.FriendshipDomain;
import org.codelab.thetext.dtos.request.FriendshipRequestDTO;
import org.codelab.thetext.persistence.entities.Friendship;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendships")
@AllArgsConstructor
public class FriendshipController {
    private final FriendshipDomain friendshipDomain;

    // Obtener todas las amistades de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Friendship>> getFriendshipsByUser(@PathVariable Long userId) {
        List<Friendship> friendships = friendshipDomain.findAllFriends(userId);
        return ResponseEntity.ok(friendships);
    }

    // Enviar solicitud de amistad
    @PostMapping("/request")
    public ResponseEntity<Friendship> sendFriendRequest(@RequestBody FriendshipRequestDTO request) {
        Friendship friendship = friendshipDomain.sendFriendRequest(request.getUserId(), request.getFriendId());
        return ResponseEntity.status(201).body(friendship);
    }

    // Aceptar solicitud de amistad
    @PostMapping("/accept/{friendshipId}")
    public ResponseEntity<Friendship> acceptFriendRequest(@PathVariable Long friendshipId) {
        Friendship friendship = friendshipDomain.acceptFriendRequest(friendshipId);
        return ResponseEntity.ok(friendship);
    }



}
