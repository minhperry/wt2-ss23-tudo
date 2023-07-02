package de.tudortmund.wt2.luminary.model.auth;

import de.tudortmund.wt2.luminary.constant.UserRole;
import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserRole userRole;

    public AuthResponseDto (String accessToken, UserRole userRole) {
        this.accessToken = accessToken;
        this.userRole = userRole;
    }
}