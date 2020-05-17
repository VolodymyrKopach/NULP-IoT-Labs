package com.db.lab5.service;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.Objects;

@Service
public class EventHubService {
    private final EventHubClient eventHubClient;

    @Autowired
    public EventHubService(EventHubClient eventHubClient) {
        this.eventHubClient = eventHubClient;
    }

    public void sendEvent(String data) {
        System.out.println("Sending message to the event hub: " + eventHubClient.getEventHubName());
        eventHubClient.send(EventData.create(Objects.requireNonNull(SerializationUtils.serialize(data))), data);
    }
}