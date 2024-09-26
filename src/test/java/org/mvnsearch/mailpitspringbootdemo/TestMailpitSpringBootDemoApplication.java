package org.mvnsearch.mailpitspringbootdemo;

import org.springframework.boot.SpringApplication;

public class TestMailpitSpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(MailpitSpringBootDemoApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
