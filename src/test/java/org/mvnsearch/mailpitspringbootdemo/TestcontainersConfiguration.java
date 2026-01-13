package org.mvnsearch.mailpitspringbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {
    private static final Logger log = LoggerFactory.getLogger(TestcontainersConfiguration.class);


}
