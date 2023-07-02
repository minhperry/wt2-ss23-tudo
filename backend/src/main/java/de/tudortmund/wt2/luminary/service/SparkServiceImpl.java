package de.tudortmund.wt2.luminary.service;

import de.tudortmund.wt2.luminary.constant.UserRole;
import de.tudortmund.wt2.luminary.entity.SparkDAO;
import de.tudortmund.wt2.luminary.entity.UserDAO;
import de.tudortmund.wt2.luminary.entity.mapper.DaoToModelMapper;
import de.tudortmund.wt2.luminary.entity.mapper.ModelToDaoMapper;
import de.tudortmund.wt2.luminary.model.auth.UserContentInfoDto;
import de.tudortmund.wt2.luminary.model.spark.SparkDto;
import de.tudortmund.wt2.luminary.repository.SparkRepository;
import de.tudortmund.wt2.luminary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SparkServiceImpl implements SparkService {
    private final SparkRepository sparkRepository;
    private final UserRepository userRepository;
    private final DaoToModelMapper daoToModelMapper;
    private final ModelToDaoMapper modelToDaoMapper;

    @Override
    public List<SparkDto> fetchAllIdeas() {
        List<SparkDAO> sparkDAOList = sparkRepository.findAll();

        return sparkDAOList.stream().map(daoToModelMapper::map).collect(Collectors.toList());
    }

    @Override
    public String createSpark(String sparkContent, UserDetails authentication) throws Exception {
        if (sparkContent.length() > 500) {
            throw new IllegalArgumentException("Spark content cannot exceed 500 characters");
        }

        String username = authentication.getUsername();
        UserDAO user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        SparkDto newSparkDto = SparkDto.builder()
                .content(sparkContent)
                .creator(UserContentInfoDto.builder().username(user.getUsername()).name(user.getName()).build())
                .build();

        try {
            SparkDAO newSparkDao = modelToDaoMapper.map(newSparkDto);
            sparkRepository.save(newSparkDao);

            return "Spark created successfully!";
        } catch (Exception e) {
            throw new Exception("Failed to create spark. Please try again later.", e);
        }
    }

    @Override
    public String updateSpark(UUID id, String newContent, UserDetails authentication) throws Exception {
        SparkDAO founded = sparkRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No Spark with this ID(%s) found", id)));

        String usernameInSpark = founded.getCreator().getUsername();
        if (Objects.equals(authentication.getUsername(), usernameInSpark)) {
            try {
                SparkDAO updated = modelToDaoMapper.update(founded, newContent);
                sparkRepository.save(updated);

                return "Update was successful";
            } catch (Exception ex) {
                throw new Exception("Failed to update Spark", ex);
            }
        }

        throw new IllegalStateException("You have no permission to edit this Spark");
    }

    @Override
    public String deleteSpark(UUID id, UserDetails authentication) throws Exception {
        SparkDAO foundedSpark = sparkRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No Spark with this Id(%s) founded", id)));

        UserDAO user = userRepository.findByUsername(authentication.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No User with this username(%s) founded", authentication.getUsername())));

        String usernameOfSpark = foundedSpark.getCreator().getUsername();

        if (user.getRole() == UserRole.ADMIN || Objects.equals(user.getUsername(), usernameOfSpark)) {
            try {
                sparkRepository.delete(foundedSpark);

                return "Spark is deleted, successfully!";
            } catch (Exception ex) {
                throw new Exception("Failed to delete Spark", ex);
            }
        }

        throw new IllegalStateException("You have no permission to delete this Spark");
    }

    @Override
    public String updateLightCount(UUID id) throws Exception {
        SparkDAO found = sparkRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException(String.format("No Spark with this ID(%s) found", id)));

        int currentLightCount = found.getLightCount();
        try {
            SparkDAO updated = modelToDaoMapper.update(found, currentLightCount + 1);
            sparkRepository.save(updated);

            return String.format("Light count is updated from %d to %d", currentLightCount, currentLightCount + 1);
        } catch (Exception ex) {
            throw new Exception("Failed to update Spark", ex);
        }
    }
}