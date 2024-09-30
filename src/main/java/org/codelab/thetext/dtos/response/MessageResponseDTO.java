package org.codelab.thetext.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MessageResponseDTO {
    private Long id;
    private String content;
    private Long senderId;
    private String senderUsername;
    private Long receiverId;
    private String receiverUsername;
    private LocalDateTime timestamp;
}
