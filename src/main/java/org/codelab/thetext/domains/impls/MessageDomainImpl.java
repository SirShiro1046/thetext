package org.codelab.thetext.domains.impls;

import lombok.AllArgsConstructor;
import org.codelab.thetext.domains.MessageDomain;
import org.codelab.thetext.dtos.MessageRequestDTO;
import org.codelab.thetext.persistence.entities.Message;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.services.MessageService;
import org.codelab.thetext.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class MessageDomainImpl implements MessageDomain {
    private final MessageService messageService;
    private final UserService userService;

    @Override
    public Message sendMessage(MessageRequestDTO messageRequest) {
        Message message = Message.builder()
                .content(messageRequest.getContent())
                .sender(new User(messageRequest.getSenderId()))
                .receiver(new User(messageRequest.getReceiverId()))
                .timestamp(LocalDateTime.now())
                .build();
        return messageService.save(message);
    }

    @Override
    public List<Message> getMessagesByUser(Long userId) {
          User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

          List<Message> sentMessages = messageService.findBySender(user);
          List<Message> receivedMessages = messageService.findByReceiver(user);

            // Combine the two lists
          List<Message>AllMessages = new ArrayList<>();
          AllMessages.addAll(sentMessages);
          AllMessages.addAll(receivedMessages);
          return AllMessages;

    }
}
