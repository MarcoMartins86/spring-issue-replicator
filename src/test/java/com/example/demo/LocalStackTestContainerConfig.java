package com.example.demo;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@TestConfiguration(proxyBeanMethods = false)
public class LocalStackTestContainerConfig {

    @Bean
    @ServiceConnection
    @RestartScope
    LocalStackContainer localStackContainer() {
        return new LocalStackContainer(DockerImageName.parse("public.ecr.aws/localstack/localstack:4.3.0")
                        .asCompatibleSubstituteFor("localstack/localstack"))
                .withServices(SQS)
                .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofMinutes(5)));
    }
}
