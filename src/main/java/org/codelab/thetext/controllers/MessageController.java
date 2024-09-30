// src/main/java/org/codelab/thetext/controllers/MessageController.java
package org.codelab.thetext.controllers;

import lombok.AllArgsConstructor;
import org.codelab.thetext.domains.MessageDomain;
import org.codelab.thetext.dtos.request.MessageRequestDTO;
import org.codelab.thetext.dtos.response.MessageResponseDTO;
import org.codelab.thetext.persistence.entities.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {
    private final MessageDomain messageDomain;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send")
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody MessageRequestDTO messageRequest) {
        Message message = messageDomain.sendMessage(messageRequest);
        messagingTemplate.convertAndSend("/topic/messages", message);

        // Map Message entity to MessageResponseDTO
        MessageResponseDTO messageDTO = new MessageResponseDTO();
        messageDTO.setId(message.getId());
        messageDTO.setContent(message.getContent());
        messageDTO.setSenderId(message.getSender().getId());
        messageDTO.setSenderUsername(message.getSender().getUsername());
        messageDTO.setReceiverId(message.getReceiver().getId());
        messageDTO.setReceiverUsername(message.getReceiver().getUsername());
        messageDTO.setTimestamp(message.getTimestamp());

        return ResponseEntity.status(201).body(messageDTO);
    }



    //refactorizar despues
@GetMapping("/user/{userId}")
public ResponseEntity<List<MessageResponseDTO>> getMessagesByUser(@PathVariable Long userId) {
    List<Message> messages = messageDomain.getMessagesByUser(userId);
    List<MessageResponseDTO> response = messages.stream().map(message -> {
        MessageResponseDTO dto = new MessageResponseDTO();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setSenderId(message.getSender().getId());
        dto.setSenderUsername(message.getSender().getUsername());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setReceiverUsername(message.getReceiver().getUsername());
        dto.setTimestamp(message.getTimestamp());
        return dto;
    }).collect(Collectors.toList());
    return ResponseEntity.ok(response);
}
}