package org.codelab.thetext.controllers;

import lombok.AllArgsConstructor;
import org.codelab.thetext.domains.UserDomain;
import org.codelab.thetext.dtos.response.ChatRoomResponseDTO;
import org.codelab.thetext.persistence.entities.ChatRoom;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.services.UserService;
import org.codelab.thetext.services.impls.ChatRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/chat-rooms")
@AllArgsConstructor
public class ChatRoomController {
    //TODO refactorizar despues a ChatRoomService con el patron de diseño de interfaces en lugar de consumir de services consumir de interface de domain
    private final ChatRoomServiceImpl chatRoomService;
    private final UserDomain userDomain;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatRoomResponseDTO>> getChatRoomsByUser(@PathVariable Long userId) {
        List<ChatRoomResponseDTO> chatRooms = chatRoomService.getChatRoomsByUser(userId);
        return ResponseEntity.ok(chatRooms);
    }

    // la idea es que para crear el chat para optener el id hay dos formas una es que tu agregues a un usuario
    // de tu lista de amigos sacar el id y la otra si quieres mandar mensaje a una persona desconocida para establecer amistad
    // se puede hacer un endpoint para buscar usuarios por nombre  y mandar solicitud de amistad
@PostMapping("/create/{name}")
public ResponseEntity<ChatRoom> createChatRoom(@PathVariable String name, @RequestBody List<Long> participantIds) {
    List<User> participants = userDomain.findAllById(participantIds);
    if (participants.size() != participantIds.size()) {
        throw new RuntimeException("Some users not found");
    }
    ChatRoom chatRoom = chatRoomService.createChatRoom(name, new HashSet<>(participants));
    return ResponseEntity.status(201).body(chatRoom);
}

}
