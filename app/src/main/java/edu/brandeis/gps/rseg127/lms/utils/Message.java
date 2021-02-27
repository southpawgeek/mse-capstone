package edu.brandeis.gps.rseg127.lms.utils;

import lombok.Data;

@Data
public class Message {
    private String type;
    private String message;

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
