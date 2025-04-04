package dev.stratospheric.todoapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DockerComposeEnvironmentVerificationTest {

  static DockerComposeContainer<?> environment =
    new DockerComposeContainer<>(new File("compose.yaml"))
      .withExposedService("postgres_1", 5432, Wait.forListeningPort())
      .withExposedService("keycloak_1", 8080, Wait.forHttp("/auth").forStatusCode(200)
        .withStartupTimeout(Duration.ofSeconds(45)))
      .withExposedService("activemq_1", 61613, Wait.forListeningPort())
      .withExposedService("localstack_1", 4566, Wait.forListeningPort())
      .withOptions("--compatibility")
      .withLocalCompose(true);

  static {
    environment.start();
  }

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void shouldStartApplicationWithDockerComposeEnvironment() {
    assertNotNull(applicationContext);
  }
}
