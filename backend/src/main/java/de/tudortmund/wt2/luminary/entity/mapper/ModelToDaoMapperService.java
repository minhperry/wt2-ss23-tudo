package de.tudortmund.wt2.luminary.entity.mapper;

import de.tudortmund.wt2.luminary.constant.UserRole;
import de.tudortmund.wt2.luminary.entity.SparkDAO;
import de.tudortmund.wt2.luminary.entity.UserDAO;
import de.tudortmund.wt2.luminary.model.auth.RegisterDto;
import de.tudortmund.wt2.luminary.model.spark.SparkDto;
import de.tudortmund.wt2.luminary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ModelToDaoMapperService implements ModelToDaoMapper{
    private final ModelToDaoMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public SparkDAO map(SparkDto sparkDto) {
        sparkDto.setCreatedAt(LocalDateTime.now());

        SparkDAO sparkDAO = mapper.map(sparkDto);

        Optional<UserDAO> creator = userRepository.findByUsername(sparkDAO.getCreator().getUsername());

        creator.ifPresent(sparkDAO::setCreator);

        return sparkDAO;
    }

    @Override
    public SparkDAO update(SparkDAO target, String update) {
        SparkDAO updated =  mapper.update(target, update);
        updated.setContent(update);
        updated.setLastEditedAt(Timestamp.valueOf(LocalDateTime.now()));

        return updated;
    }

    @Override
    public SparkDAO update(SparkDAO target, Integer lightCount) {
        SparkDAO updated =  mapper.update(target, lightCount);
        updated.setLightCount(lightCount);

        return updated;
    }

    @Override
    public UserDAO map(RegisterDto registerDto) {
        UserDAO userDAO = mapper.map(registerDto);

        String encryptedPassword = passwordEncoder.encode(registerDto.getPassword());
        userDAO.setPassword(encryptedPassword);

        // Default Role is USER
        userDAO.setRole(UserRole.USER);

        return userDAO;
    }
}