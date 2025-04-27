package com.javaprojects.ecommerce.service;

import com.javaprojects.ecommerce.model.*;
import com.javaprojects.ecommerce.repository.BlacklistedTokenRepository;
import com.javaprojects.ecommerce.repository.ConfirmationTokenRepository;
import com.javaprojects.ecommerce.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Transactional
    public String register(RegistrationRequest request) throws AccessDeniedException, MessagingException {
        String email = request.getEmail();
        if(userRepository.existsByEmail(email)){
            User existingUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found!"));
            if(existingUser.isEnabled()){
                throw new AccessDeniedException("User already exists!");
            } else if(existingUser.getToken().getExpiresAt().isBefore(LocalDateTime.now())){
                confirmationTokenRepository.delete(existingUser.getToken());
                String token = UUID.randomUUID().toString();
                ConfirmationToken confirmationToken = new ConfirmationToken(token, existingUser);
                existingUser.setToken(confirmationToken);
                userRepository.save(existingUser);
                mailService.sendConfirmationEmail(token, existingUser.getEmail());
                return "A new verification email has been sent.";
            }
            return "Please check your email for verification.";
        }
        User user = new User();
        user.setEmail(email);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(false);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, user);

        user.setToken(confirmationToken);
        userRepository.save(user);
        mailService.sendConfirmationEmail(token, user.getEmail());
        return "Verification email sent!";
    }
    @Transactional
    public String verify(String token){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        confirmationTokenRepository.delete(user.getToken());
        return "Your account has been verified!";
    }

    public String login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found!"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Incorrect password!");
        }
        return jwtService.generateToken(user.getEmail());
    }

    @Transactional
    public ResponseEntity<String> logout(HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")){
            return ResponseEntity.badRequest().body("Invalid token");
        }

        String token = header.substring(7);

        if(!blacklistedTokenRepository.existsByToken(token)){
            blacklistedTokenRepository.save(
                    BlacklistedToken.builder()
                            .token(token)
                            .blacklistedAt(Instant.now())
                            .build()
            );
        }
        return ResponseEntity.ok().body("Successfully logged out");
    }
}
