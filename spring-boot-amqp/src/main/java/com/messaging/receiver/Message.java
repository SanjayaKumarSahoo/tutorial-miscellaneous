package com.messaging.receiver;

/**
 * Created by sanjaya on 10/23/16.
 */
public class Message {

    public Message(String data) {
        this.data = data;
    }

    private String data;

    public Message() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
