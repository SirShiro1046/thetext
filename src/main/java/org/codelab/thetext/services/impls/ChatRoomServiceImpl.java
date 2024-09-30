package org.codelab.thetext.services.impls;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codelab.thetext.domains.MessageDomain;
import org.codelab.thetext.dtos.response.ChatRoomResponseDTO;
import org.codelab.thetext.dtos.response.MessageResponseDTO;
import org.codelab.thetext.persistence.entities.ChatRoom;
import org.codelab.thetext.persistence.entities.Message;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.persistence.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Log4j2
public class ChatRoomServiceImpl {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageDomain messageDomain;

    public List<ChatRoomResponseDTO> getChatRoomsByUser(Long userId) {
        log.info("Buscando salas de chat por usuario: {}", userId);
        List<ChatRoom> chatRooms = chatRoomRepository.findByParticipantsId(userId);
        return chatRooms.stream().map(chatRoom -> convertToDTO(chatRoom, userId)).collect(Collectors.toList());
    }

    private ChatRoomResponseDTO convertToDTO(ChatRoom chatRoom, Long userId) {
        log.info("Convirtiendo sala de chat a DTO: {}", chatRoom);
        ChatRoomResponseDTO dto = new ChatRoomResponseDTO();
        dto.setId(chatRoom.getId());
        dto.setName(chatRoom.getName());
        List<Message> messages = messageDomain.getMessagesByUser(userId);
        dto.setMessages(messages.stream().map(this::convertMessageToDTO).collect(Collectors.toList()));
        return dto;
    }

    private MessageResponseDTO convertMessageToDTO(Message message) {
        log.info("Convirtiendo mensaje a DTO: {}", message);
        MessageResponseDTO dto = new MessageResponseDTO();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setSenderId(message.getSender().getId());
        dto.setSenderUsername(message.getSender().getUsername());
        dto.setReceiverId(message.getReceiver().getId());
        dto.setReceiverUsername(message.getReceiver().getUsername());
        dto.setTimestamp(message.getTimestamp());
        return dto;
    }

        public ChatRoom createChatRoom(String name, Set<User> participants) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setName(name);
            chatRoom.setParticipants(participants);
            chatRoom.setCreatedAt(LocalDateTime.now());
            return chatRoomRepository.save(chatRoom);
        }

}
