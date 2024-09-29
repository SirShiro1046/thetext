// src/main/java/org/codelab/thetext/controllers/MessageController.java
package org.codelab.thetext.controllers;

import lombok.AllArgsConstructor;
import org.codelab.thetext.domains.MessageDomain;
import org.codelab.thetext.dtos.MessageRequestDTO;
import org.codelab.thetext.persistence.entities.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {
    private final MessageDomain messageDomain;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequestDTO messageRequest) {
        Message message = messageDomain.sendMessage(messageRequest);
        messagingTemplate.convertAndSend("/topic/messages", message);
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Long userId) {
        List<Message> messages = messageDomain.getMessagesByUser(userId);
        return ResponseEntity.ok(messages);
    }
}