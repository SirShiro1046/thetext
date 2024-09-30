package org.codelab.thetext.dtos.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestDTO {
    private Long senderId; // ID del usuario que envía el mensaje
    private Long receiverId; // ID del usuario que recibe el mensaje
    private String content; // Contenido del mensaje
}
