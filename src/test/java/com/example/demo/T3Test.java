package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@IntegrationTest
@EnableWireMock(@ConfigureWireMock(name = "test3"))
public class T3Test extends TestBase {
    @Autowired
    private SqsAsyncClient sqsAsyncClient;

    @Autowired
    private Listener listener;

    @Test
    public void test() throws InterruptedException {
        ListenerLookup.populate(listener, "Test3");

        sqsAsyncClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(Listener.QUEUE_NAME)
                .messageBody(listener.toString())
                .build());

        await()
                .atMost(Duration.ofSeconds(5))
                .with()
                .pollInterval(Duration.ofMillis(250))
                .untilAsserted(() -> assertTrue(getResult()));
    }
}
