package org.codelab.thetext.services;

import org.codelab.thetext.persistence.entities.Message;
import org.codelab.thetext.persistence.entities.User;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message save(Message message);
    Optional<Message> findById(Long id);
    List<Message> findAll();
    void deleteById(Long id);
    Long count ();
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
}
