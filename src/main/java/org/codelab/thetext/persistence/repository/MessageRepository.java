package org.codelab.thetext.persistence.repository;

import org.codelab.thetext.persistence.entities.Message;
import org.codelab.thetext.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository  extends JpaRepository<Message, Long> {
    // Encuentra mensajes entre dos usuarios por su ID
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
}
