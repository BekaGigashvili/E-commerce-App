package com.javaprojects.ecommerce.controller;

import com.javaprojects.ecommerce.model.RegistrationRequest;
import com.javaprojects.ecommerce.model.User;
import com.javaprojects.ecommerce.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/register")
    public String Register(@RequestBody RegistrationRequest request) throws AccessDeniedException, MessagingException {
        return userService.register(request);
    }
    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token){
        return userService.verify(token);
    }
}
