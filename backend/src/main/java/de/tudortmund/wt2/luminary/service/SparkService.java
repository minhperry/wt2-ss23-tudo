package de.tudortmund.wt2.luminary.service;

import de.tudortmund.wt2.luminary.model.spark.SparkDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface SparkService {
    List<SparkDto> fetchAllIdeas();

    String createSpark(String sparkContent, UserDetails authentication) throws Exception;

    String updateSpark(UUID id, String newContent, UserDetails authentication) throws Exception;

    String deleteSpark(UUID id, UserDetails authentication) throws Exception;

    String updateLightCount(UUID id) throws Exception;
}