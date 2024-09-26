package org.mvnsearch.mailpitspringbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * portal controller
 *
 * @author linux_china
 */
@RestController
public class PortalController {
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String index() {
        return "Hello, Mailpit!";
    }

    public record MailRequest(String from, String to, String subject, String body) {
    }

    @PostMapping("/send-email")
    public String send(@RequestBody MailRequest request) throws Exception {
        var mimeMessage = mailSender.createMimeMessage();
        var message = new MimeMessageHelper(mimeMessage);
        message.setFrom(request.from());
        message.setTo(request.to());
        message.setSubject(request.subject());
        message.setText(request.body(), false);
        mailSender.send(message.getMimeMessage());
        return "Mail sent!";
    }
}
