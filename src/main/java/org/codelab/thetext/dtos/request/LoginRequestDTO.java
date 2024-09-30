package org.codelab.thetext.dtos.request;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}