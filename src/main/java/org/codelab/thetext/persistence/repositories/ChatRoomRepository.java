package org.codelab.thetext.persistence.repositories;

import org.codelab.thetext.persistence.entities.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByParticipantsId(Long userId);
}
