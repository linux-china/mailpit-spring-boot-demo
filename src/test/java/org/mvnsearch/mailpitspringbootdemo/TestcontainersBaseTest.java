package org.mvnsearch.mailpitspringbootdemo;

import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Spring Boot base test case with TestContainers suppoort
 *
 * @author linux_china
 */
@Import(TestcontainersConfiguration.class)
@Testcontainers
public abstract class TestcontainersBaseTest extends SpringBootBaseTest {

}
