package org.mvnsearch.mailpitspringbootdemo;

import org.mvnsearch.mailpitspringbootdemo.mail.MailpitClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration(proxyBeanMethods = false)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class TestcontainersConfiguration {
    @Bean
    public GenericContainer<?> mailpit(DynamicPropertyRegistry properties) {
        GenericContainer<?> mailpit = new GenericContainer<>("axllent/mailpit:v1.20.4").withExposedPorts(1025, 8025);
        properties.add("spring.mail.host", mailpit::getHost);
        properties.add("spring.mail.port", () -> mailpit.getMappedPort(1025).toString());
        return mailpit;
    }

    @Bean
    @DependsOn("mailpit")
    public MailpitClient mailpitClient(Environment environment) {
        String host = environment.getProperty("spring.mail.host");
        String port = environment.getProperty("spring.mail.port");
        String httpBaseUrl = "http://" + host + ":" + port;
        RestClient restClient = RestClient.builder().baseUrl(httpBaseUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(MailpitClient.class);
    }
}
