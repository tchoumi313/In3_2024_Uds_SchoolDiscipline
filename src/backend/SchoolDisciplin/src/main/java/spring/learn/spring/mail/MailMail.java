package spring.learn.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailMail")
public class MailMail {

    @Autowired
    private JavaMailSender mailSender;

    private SimpleMailMessage customeMailMessage;

    public void sendMail(String dear, String content, String fileName) {

        MimeMessage message = mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(customeMailMessage.getFrom());
            helper.setTo(customeMailMessage.getTo());
            helper.setSubject(customeMailMessage.getSubject());
            helper.setText(String.format(
                    customeMailMessage.getText(), dear, content));

            FileSystemResource file = new FileSystemResource(fileName);

            helper.addAttachment(file.getFilename(), file);

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }

        mailSender.send(message);

    }

    public void sendMailAlert(String subject, String content, String emailTo) {

        MimeMessage message = mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("noreply@dd.eu");
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(String.format(content));

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }

        mailSender.send(message);

    }

    public void sendMailWithAttachment(String sender, String toEmail, String body, String subject, String attachment)
            throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(sender);
        messageHelper.setTo(toEmail);
        messageHelper.setText(body);
        messageHelper.setSubject(subject);

        FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
        messageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
        try {
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new MailException("mail with attachment not send to customer") {
            };
        }
    }

    public void sendSimpleMail(String sender, String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            throw new MailException("Simple mail not send to customer") {
            };
        }
    }

    public void sendMailWithAttachment1(ByteArrayOutputStream baos, String sender, String toEmail, String body,
            String subject, String attachment)
            throws MessagingException {
        byte[] csvBytes = baos.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(csvBytes);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(sender);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);

        helper.addAttachment(attachment, resource);

        mailSender.send(message);
    }
}
