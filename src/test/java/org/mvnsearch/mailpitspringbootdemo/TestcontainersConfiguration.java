package org.mvnsearch.mailpitspringbootdemo;

import org.mvnsearch.mailpitspringbootdemo.mail.MailpitClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration(proxyBeanMethods = false)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class TestcontainersConfiguration {
    private static final Logger log = LoggerFactory.getLogger(TestcontainersConfiguration.class);

    @Bean
    public GenericContainer<?> mailpit(DynamicPropertyRegistry properties) {
        GenericContainer<?> mailpit = new GenericContainer<>("axllent/mailpit:v1.20.4")
                //.waitingFor(Wait.forLogMessage(".*accessible.*", 1))
                .withExposedPorts(1025, 8025);
        properties.add("spring.mail.host", mailpit::getHost);
        properties.add("spring.mail.port", () -> mailpit.getMappedPort(1025).toString());
        properties.add("mailpit.web.port", () -> mailpit.getMappedPort(8025));
        return mailpit;
    }

    @Bean
    public ApplicationRunner logMailpitWebPort(@Value("${spring.mail.host}") String host,
                                               @Value("${mailpit.web.port}") int port) {
        return args -> log.info("Mailpit Web Interface http://{}:{}", host, port);
    }

    @Bean
    @DependsOn("mailpit")
    public MailpitClient mailpitClient(@Value("${spring.mail.host}") String host,
                                       @Value("${mailpit.web.port}") int webPort) {
        String httpBaseUrl = "http://" + host + ":" + webPort;
        RestClient restClient = RestClient.builder().baseUrl(httpBaseUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(MailpitClient.class);
    }
}
