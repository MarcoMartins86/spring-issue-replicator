package com.example.demo;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Controller;

@Controller
public class Listener {
    public static final String QUEUE_NAME = "testQueue";

    private final ListenerLookup listenerLookup;
    public Listener(ListenerLookup listenerLookup) {
        this.listenerLookup = listenerLookup;
    }

    @SqsListener(QUEUE_NAME)
    void handle(String message) {
        listenerLookup.compare(this, message);
    }
}
