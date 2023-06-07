package com.fp.finpoint.global.util;

import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final ResourceLoader resourceLoader;

    @Value("${login.mail.subject}")
    private String SUBJECT;

    @Value("${login.mail.imagePath}")
    private String IMAGE_PATH;

    @Value("${login.mail.imageId}")
    private String IMAGE_ID;

    @Value("${login.mail.sender}")
    private String SENDER;

    @Async
    public void sendHtmlMessageWithInlineImage(String to, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(SENDER);
            helper.setTo((to));
            helper.setSubject(SUBJECT);

            String htmlEmail = buildHtmlEmail(code);
            helper.setText(htmlEmail, true);

            Resource resource = resourceLoader.getResource("classpath:" + IMAGE_PATH);
            helper.addInline(IMAGE_ID, resource);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_TRANSFER_FAIL);
        }
    }

    private String buildHtmlEmail(String message) {
        Context context = new Context();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenMinutesLater = now.plusMinutes(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        context.setVariable("message", message);
        context.setVariable("imageId", IMAGE_ID);
//        context.setVariable("currentTime", now.format(formatter));
        context.setVariable("tenMinutesLater", tenMinutesLater.format(formatter));
        return templateEngine.process("login-mail", context);
    }

}
