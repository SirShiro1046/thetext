package org.codelab.thetext.dtos.response;

import lombok.Data;
import java.util.List;

@Data
public class ChatRoomResponseDTO {
    private Long id;
    private String name;
    private List<MessageResponseDTO> messages;
}