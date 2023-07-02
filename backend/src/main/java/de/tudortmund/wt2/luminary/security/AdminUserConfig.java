package de.tudortmund.wt2.luminary.security;

import de.tudortmund.wt2.luminary.constant.UserRole;
import de.tudortmund.wt2.luminary.entity.UserDAO;
import de.tudortmund.wt2.luminary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Check if the admin user already exists in the database
        if (!userRepository.existsByUsername("admin")) {

            // Create a new admin user
            UserDAO adminUser = new UserDAO();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));// Encode the password
            adminUser.setName("ADMIN of Luminary");
            adminUser.setRole(UserRole.ADMIN);

            // Save the admin user in the database
            userRepository.save(adminUser);
        }
    }
}
