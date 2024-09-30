package org.codelab.thetext.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipRequestDTO {
    private Long userId;  // ID del usuario que envía la solicitud
    private Long friendId; // ID del amigo que recibe la solicitud
}
