package org.codelab.thetext.controllers;


import lombok.AllArgsConstructor;
import org.apache.catalina.mbeans.UserMBean;
import org.codelab.thetext.domains.UserDomain;
import org.codelab.thetext.dtos.UserRequestDTO;
import org.codelab.thetext.persistence.entities.User;
import org.codelab.thetext.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserDomain userDomain;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequest) {
        User user = userDomain.save(userRequest);
        return ResponseEntity.status(201).body(user);
    }
}