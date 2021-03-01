package edu.brandeis.gps.rseg127.lms.utils;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Message {
    private String type;
    private String message;

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }

    // default message type
    public Message(String message) {
        this.type = "default";
        this.message = message;
    }

    // default empty message
    public Message() {
        this.type = "default";
        this.message = "This is an empty message.";
    };
}
