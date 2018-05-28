package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.MimeTypeConstants;
import com.atibusinessgroup.fmp.domain.User;
import com.atibusinessgroup.fmp.domain.WorkPackage.Attachment;

import io.github.jhipster.config.JHipsterProperties;

import org.apache.commons.lang3.CharEncoding;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Service for sending emails.
 * <p>
 * We use the @Async annotation to send emails asynchronously.
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "user";

    private static final String BASE_URL = "baseUrl";

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public MailService(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender,
            MessageSource messageSource, SpringTemplateEngine templateEngine) {

        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String from, String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] from '{}' to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, from, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendEmailFromTemplate(String from, User user, String templateName, String titleKey) {
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(from, from);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(from, user.getEmail(), subject, content, false, true);

    }

    @Async
    public void sendEmailFromTemplateWithCustomVariable(String from, User user, String templateName, String titleKey, Map<String, String> customVariable) {
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(from, from);
        context.setVariable("map", customVariable);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(from, user.getEmail(), subject, content, false, true);
    }
    
    @Async
    public void sendEmailWithoutAttachment(String from, String to[], String cc[], String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setFrom(from);
            message.setTo(to);
            
            if(cc != null) {
            	message.setCc(cc);
            }
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }
    
    @Async
    public void sendEmailWithAttachment(String from, String to[], String cc[], String subject, String content, boolean isMultipart, boolean isHtml, List<Attachment> data) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            
            
            log.debug("ATTACHMENT SIZE : {}", data.size());
            int i=0;
            for(Attachment attachment : data) {
            	MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
                MimeType mmType = allTypes.forName(attachment.getFileContentType());
                String ext = mmType.getExtension(); // .jpg
                
                log.debug("SEND ATTACHMENT WITH EXTENSION : {}", ext);
                ByteArrayResource byteArray = new ByteArrayResource(attachment.getFile());
                message.addAttachment("Attachment-"+i+"."+ext, byteArray);            	
                i++;
            }
            message.setFrom(from);
            message.setTo(to);
            if(cc != null) {
                message.setCc(cc);
            }
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }
    
    @Async
    public void sendActivationEmail(String from, User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(from, user, "activationEmail", "email.activation.title");
    }

    @Async
    public void sendCreationEmail(String from, User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(from, user, "creationEmail", "email.activation.title");
    }

    @Async
    public void sendPasswordResetMail(String from, User user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(from,user, "passwordResetEmail", "email.reset.title");
    }
    @Async
    public void sendChangedFieldMail(String from, User user, List<String> changedField) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        Map<String, String> customField = new HashMap<>();
        customField.put("field", changedField.toString());
        sendEmailFromTemplateWithCustomVariable(from, user, "userChangedField", "email.change.title", customField);
    }
}
