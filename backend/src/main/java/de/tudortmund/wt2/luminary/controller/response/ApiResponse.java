package de.tudortmund.wt2.luminary.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@JsonInclude(Include.NON_NULL)
public class ApiResponse {
    private final HttpStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timeStamp;
    private final String message;

    public ApiResponse(String message) {
        this.status = HttpStatus.OK;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
    }

    public ResponseEntity<ApiResponse> buildApiResponse(){
        return new ResponseEntity<>(this, this.getStatus());
    }
}