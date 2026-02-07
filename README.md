Mailpit Spring Boot demo
===========================
 
![Mailpit+TestContainers+Spring Boot](banner.png)

Mailpit - email & SMTP testing tool with API for developers.

Mailpit is a small, fast, low memory, zero-dependency, multi-platform email testing tool & API for developers.

It acts as an SMTP server, provides a modern web interface to view & test captured emails, and contains an API for
automated integration testing.
                                  

# MailpitClient

`MailpitClient` is a Spring HTTP interface to the Mailpit API.

```java

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;import com.fasterxml.jackson.annotation.JsonProperty;import org.springframework.web.service.annotation.GetExchange;import java.util.List;public interface MailpitClient {

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
```

# References

* Mailpit: email testing for developers - https://mailpit.axllent.org/
* [testcontainers-mailpit](https://github.com/martinellich/testcontainers-mailpit): A Testcontainers module for Mailpit - an email and SMTP testing tool with API for developers.
* Using Mailpit with Spring Boot: https://dimitri.codes/spring-boot-mailpit/
* [Docker Mailserver](https://github.com/docker-mailserver/docker-mailserver): Production-ready fullstack but simple mail server (SMTP, IMAP, LDAP, Antispam, Antivirus, etc.) running inside a container.
* Testing Emails with Testcontainers and Mailpit: https://foojay.io/today/testing-emails-with-testcontainers-and-mailpit/

