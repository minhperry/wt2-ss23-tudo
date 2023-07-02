package de.tudortmund.wt2.luminary.controller.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private final HttpStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timeStamp;
    private final String message;

    public ErrorResponse(HttpStatus status, String message) {
        timeStamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
}