package org.mvnsearch.mailpitspringbootdemo.mail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Mailpit client test
 *
 * @author linux_china
 */
public class MailpitTest {
    private static MailpitClient mailpitClient;

    @BeforeAll
    public static void setUp() {
        RestClient restClient = RestClient.builder().baseUrl("http://localhost:8025").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        mailpitClient = factory.createClient(MailpitClient.class);
    }

    @Test
    public void testListMessages() {
        var messagesResponse = mailpitClient.listMessages();
        messagesResponse.messages().forEach(message -> {
            System.out.println(message.subject());
        });
    }
}
