package org.codelab.thetext.domains;

import org.codelab.thetext.dtos.MessageRequestDTO;
import org.codelab.thetext.persistence.entities.Message;

import java.util.List;

public interface MessageDomain {
    Message sendMessage(MessageRequestDTO messageRequest);
    List<Message> getMessagesByUser(Long userId);
}
