package org.mvnsearch.mailpitspringbootdemo.mail;

import org.junit.jupiter.api.Test;
import org.mvnsearch.mailpitspringbootdemo.TestcontainersBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * mail sender test
 *
 * @author linux_china
 */
public class MailSenderTest extends TestcontainersBaseTest {
    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSendEmail() throws Exception {
        var mimeMessage = mailSender.createMimeMessage();
        var message = new MimeMessageHelper(mimeMessage);
        var content = """
                <html>
                <h1>This is a test email</h1>
                <p>Please do not respond to this email.</p>
                </html>
                """;
        message.setFrom("noreply@example.org");
        message.setTo("me@example.org");
        message.setSubject("Test email");
        message.setText(content, true);
        mailSender.send(message.getMimeMessage());
    }
}
