Mailpit Spring Boot demo
===========================

Mailpit - email & SMTP testing tool with API for developers.
Mailpit is a small, fast, low memory, zero-dependency, multi-platform email testing tool & API for developers.
It acts as an SMTP server, provides a modern web interface to view & test captured emails, and contains an API for
automated integration testing.

# MailpitClient

`MailpitClient` is a Spring HTTP interface to the Mailpit API.

```java

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
```

# References

* Mailpit: email testing for developers - https://mailpit.axllent.org/
* Using Mailpit with Spring Boot: https://dimitri.codes/spring-boot-mailpit/

