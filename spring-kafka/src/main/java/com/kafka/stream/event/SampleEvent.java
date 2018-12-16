package com.kafka.stream.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleEvent {

    private String id;

    @JsonProperty("message")
    private Message message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SampleEvent{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
