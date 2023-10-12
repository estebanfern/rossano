package com.efernandez.rossano.service.impl;

import com.efernandez.rossano.config.MailConfiguration;
import com.efernandez.rossano.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    private final MailConfiguration mailConfiguration;
    private final Configuration configuration;
    private final JavaMailSender javaMailSender;

    public MailServiceImpl(MailConfiguration mailConfiguration,
                           Configuration configuration,
                           JavaMailSender javaMailSender) {
        this.mailConfiguration = mailConfiguration;
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendNewUserEmail(String email, String nombre, String username, String password) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED,
                    StandardCharsets.UTF_8.name());
            Template t = configuration.getTemplate("newUser.ftl");
            Map<String, String> fields = new HashMap<>();
            fields.put("name", nombre);
            fields.put("username", username);
            fields.put("password", password);
            String message = FreeMarkerTemplateUtils.processTemplateIntoString(t, fields);
            helper.setFrom(mailConfiguration.getUsername());
            helper.setTo(email);
            helper.setSubject("Creación de Usuario");
            helper.setText(message, true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            e.printStackTrace();
            sendNewUserEmailBasic(email, nombre, username, password);
        }
    }

    public void sendNewUserEmailBasic(String email, String nombre, String username, String password) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(mailConfiguration.getUsername());
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Creación de Usuario");
            mimeMessageHelper.setText(String.format(
                    "Hola %s!\n" +
                            "Le informamos que su usuario se ha creado satisfactoriamente\n" +
                            "Tus credenciales son:\n" +
                            "Usuario: %s\n" +
                            "Contraseña: %s\n" +
                            "Por favor, actualice su contraseña en la brevedad posible.",
                    nombre,
                    username,
                    password
            ));
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
