package de.tudortmund.wt2.luminary.service;

import de.tudortmund.wt2.luminary.constant.UserRole;
import de.tudortmund.wt2.luminary.entity.UserDAO;
import de.tudortmund.wt2.luminary.entity.mapper.ModelToDaoMapper;
import de.tudortmund.wt2.luminary.model.auth.AuthResponseDto;
import de.tudortmund.wt2.luminary.model.auth.LoginDto;
import de.tudortmund.wt2.luminary.model.auth.RegisterDto;
import de.tudortmund.wt2.luminary.repository.UserRepository;
import de.tudortmund.wt2.luminary.security.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ModelToDaoMapper modelToDaoMapper;

    @Override
    public String registration(RegisterDto user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getName() == null) {
            throw new IllegalArgumentException("Username, password and name are required.");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        if (user.getUsername().length() < 5 || user.getPassword().length() < 5) {
            throw new IllegalArgumentException("Username and password must have a minimum length of 5 characters.");
        }

        try {
            UserDAO newUser = modelToDaoMapper.map(user);

            if(Objects.equals(user.getName(), "admin")){
                newUser.setRole(UserRole.ADMIN);
            }

            userRepository.save(newUser);

            return "User created successfully!";
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create user.", ex);
        }
    }

    @Override
    public AuthResponseDto login(LoginDto user) throws Exception {
        Optional<UserDAO> fetchedUser = userRepository.findByUsername(user.getUsername());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.generateToken(authentication);

            UserRole userRole = null;
            if (fetchedUser.isPresent()){
                userRole = fetchedUser.get().getRole();
            }

            return new AuthResponseDto(token, userRole);
        } catch (AuthenticationException ex) {
            throw new AuthenticationServiceException("Invalid username or password");
        } catch (Exception ex) {
            throw new Exception("Failed to Login with this user and pass.", ex);
        }
    }
}