package com.ssafy.enjoytrip.exception;

import java.io.Serial;

public class EmailNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailNotFoundException(String message) {
        super(message);
    }
}