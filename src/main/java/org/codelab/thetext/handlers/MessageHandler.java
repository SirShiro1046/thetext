package org.codelab.thetext.handlers;

import org.codelab.thetext.dtos.MessageRequestDTO;
import org.codelab.thetext.persistence.entities.Message;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.services.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class MessageHandler {

    private final MessageService messageService;

    public MessageHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message sendMessage(MessageRequestDTO messageRequest) {
        Message message = Message.builder()
                .content(messageRequest.getContent())
                .sender(new User(messageRequest.getSenderId()))
                .receiver(new User(messageRequest.getReceiverId()))
                .timestamp(LocalDateTime.now())
                .build();
        return messageService.save(message);
    }
}