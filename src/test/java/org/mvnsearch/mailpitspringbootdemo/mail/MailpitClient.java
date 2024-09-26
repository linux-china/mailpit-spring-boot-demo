package org.mvnsearch.mailpitspringbootdemo.mail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * Mailpit client
 *
 * @author linux_china
 */
@HttpExchange("http://localhost:8025")
public interface MailpitClient {

    record MailAddress(@JsonProperty("Name") String name, @JsonProperty("Address") String address) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Message(@JsonProperty("ID") String ID,
                   @JsonProperty("From")
                   MailAddress from,
                   @JsonProperty("To")
                   List<MailAddress> to,
                   @JsonProperty("Subject")
                   String subject,
                   @JsonProperty("Snippet")
                   String Snippet) {

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    record MessagesResponse(Integer total, List<Message> messages) {
    }

    @GetExchange("/api/v1/messages")
    MessagesResponse listMessages();
}
