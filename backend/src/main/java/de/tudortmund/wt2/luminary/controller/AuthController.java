package de.tudortmund.wt2.luminary.controller;

import de.tudortmund.wt2.luminary.controller.response.ApiResponse;
import de.tudortmund.wt2.luminary.model.auth.AuthResponseDto;
import de.tudortmund.wt2.luminary.model.auth.LoginDto;
import de.tudortmund.wt2.luminary.model.auth.RegisterDto;
import de.tudortmund.wt2.luminary.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController implements AuthBaseController {
    private final AuthService authService;
    @Override
    public ResponseEntity<ApiResponse> registration(RegisterDto user) {
        return new ApiResponse(authService.registration(user)).buildApiResponse();
    }

    @Override
    public ResponseEntity<AuthResponseDto> login(LoginDto user) throws Exception {
        AuthResponseDto response = authService.login(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}