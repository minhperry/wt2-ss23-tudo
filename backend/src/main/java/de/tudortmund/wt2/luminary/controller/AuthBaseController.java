package de.tudortmund.wt2.luminary.controller;

import de.tudortmund.wt2.luminary.controller.response.ApiResponse;
import de.tudortmund.wt2.luminary.model.auth.AuthResponseDto;
import de.tudortmund.wt2.luminary.model.auth.LoginDto;
import de.tudortmund.wt2.luminary.model.auth.RegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthBaseController {
    @PostMapping(path = "register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "REGISTER A NEW ACCOUNT")
    ResponseEntity<ApiResponse> registration(@RequestBody RegisterDto user);

    @PostMapping(path = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "LOGIN IN EXISTING ACCOUNT")
    ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto user) throws Exception;
}