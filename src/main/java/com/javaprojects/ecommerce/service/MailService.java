package com.javaprojects.ecommerce.service;

import com.javaprojects.ecommerce.model.ConfirmationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendConfirmationEmail(String token, String receiverEmail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        String emailContent = "<div>"
                + "<h2>Confirm your email</h2>"
                + "<p>Please click the link below to activate your account:</p>"
                + "<a href=\"http://localhost:8080/user/verify?token=" + token + "\">Activate Now</a>"
                + "</div>";

        mimeMessageHelper.setTo(receiverEmail);
        mimeMessageHelper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }
}
