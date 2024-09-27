package org.mvnsearch.mailpitspringbootdemo.mail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

/**
 * Simple Java mail test
 *
 * @author linux_china
 */
public class SimpleJavaMailTest {
    private static Mailer mailer;

    @BeforeAll
    public static void setUp() {
        mailer = MailerBuilder
                .withSMTPServer("localhost", 1025)
                .buildMailer();
    }

    @AfterAll
    public static void tearDown() throws Exception {
        mailer.close();
    }

    @Test
    public void testSend() throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from("admin", "admin@example.com")
                .to("jackie", "jackie@example.com")
                .withSubject("hey")
                .withPlainText("We should meet up! ;)")
                .buildEmail();
        mailer.sendMail(email);
    }

}
