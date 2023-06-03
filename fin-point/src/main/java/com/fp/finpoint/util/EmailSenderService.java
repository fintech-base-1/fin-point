package com.fp.finpoint.util;

import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

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

    public String sendHtmlMessageWithInlineImage(String to) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(SENDER);
            helper.setTo((to));
            helper.setSubject(SUBJECT);

            UUID uuid = UUID.randomUUID();
            String htmlEmail = buildHtmlEmail(uuid.toString());
            helper.setText(htmlEmail, true);

            Resource resource = resourceLoader.getResource("classpath:" + IMAGE_PATH);
            helper.addInline(IMAGE_ID, resource);
            mailSender.send(message);

            return uuid.toString();
        } catch (MessagingException e) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_TRANSFER_FAIL);
        }
    }

    private String buildHtmlEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("imageId", IMAGE_ID);
        return templateEngine.process("login-mail", context);
    }

}
