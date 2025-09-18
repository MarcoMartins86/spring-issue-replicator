package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ListenerLookup {
    private static final ConcurrentMap<String, String> lookup = new ConcurrentHashMap<>();

    public static void populate(Listener listener, String test) {
        lookup.put(listener.toString(), test);
    }

    public boolean compare(Listener listener, String message) {
        System.out.printf("Listener from [%s] consuming message from [%s]%n", lookup.get(listener.toString()), lookup.get(message));
        return listener.toString().equals(message);
    }
}
