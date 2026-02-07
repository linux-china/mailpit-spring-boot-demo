package org.mvnsearch.mailpitspringbootdemo;

import ch.martinelli.oss.testcontainers.mailpit.MailpitContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {
    private static final Logger log = LoggerFactory.getLogger(TestcontainersConfiguration.class);

    @Bean
    @ServiceConnection
    MailpitContainer mailpitContainer() {
        return new MailpitContainer(DockerImageName.parse("axllent/mailpit:v1.28"));
    }

}
