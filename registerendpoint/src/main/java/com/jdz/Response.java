package com.jdz;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Response {
    private String message;
    private String timestamp;

    public Response(String message) {
        this.message = message;
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
    }
}
