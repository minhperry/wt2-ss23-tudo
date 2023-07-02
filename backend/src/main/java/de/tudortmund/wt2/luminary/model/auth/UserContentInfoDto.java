package de.tudortmund.wt2.luminary.model.auth;

import de.tudortmund.wt2.luminary.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContentInfoDto {
    private String username;
    private String name;
}