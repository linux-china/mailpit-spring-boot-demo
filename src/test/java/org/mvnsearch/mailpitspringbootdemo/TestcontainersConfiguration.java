package org.mvnsearch.mailpitspringbootdemo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {
    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GenericContainer<?> mailpit(DynamicPropertyRegistry properties) {
        GenericContainer<?> mailpit = new GenericContainer<>("axllent/mailpit:v1.20.4").withExposedPorts(1025, 8025);
        properties.add("spring.mail.host", mailpit::getHost);
        properties.add("spring.mail.port", () -> mailpit.getMappedPort(1025).toString());
        return mailpit;
    }
}
