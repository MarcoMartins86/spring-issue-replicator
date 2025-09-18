package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;

public abstract class TestBase {
    @MockitoSpyBean
    ListenerLookup listenerLookup;

    private static final AtomicBoolean comparisonResult = new AtomicBoolean(false);

    @BeforeEach
    void setup() {
        doAnswer(invocationOnMock -> {
            var result = (boolean)invocationOnMock.callRealMethod();
            comparisonResult.set(result);
            return result;
        }).when(listenerLookup).compare(any(Listener.class), anyString());
    }

    protected static boolean getResult()
    {
        return comparisonResult.getAndSet(false);
    }
}
