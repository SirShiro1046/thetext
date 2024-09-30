package org.codelab.thetext.dtos.request;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;
    private String name;
    private String description;
    private String password;
}