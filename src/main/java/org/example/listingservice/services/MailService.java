package org.example.listingservice.services;

import jakarta.mail.internet.MimeMessage;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.example.listingservice.models.Payment;
import org.example.listingservice.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;


    public String  sendHtmlEmail(String toEmail, String subject, Payment payment) throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setText(payment.toHtmlBody(), true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setFrom(mailFrom);

        javaMailSender.send(mimeMessage);
        return MessageKeys.MAIL_SENT_SUCCESSFULLY + " to " + toEmail;
    }

    public String sendHtmlEmail(String toEmail, String subject, User user) throws Exception{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");

        helper.setText(user.toHTMLBody(),true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setFrom(mailFrom);
        javaMailSender.send(mimeMessage);

        return MessageKeys.MAIL_SENT_SUCCESSFULLY + " to " + toEmail;
    }
}
