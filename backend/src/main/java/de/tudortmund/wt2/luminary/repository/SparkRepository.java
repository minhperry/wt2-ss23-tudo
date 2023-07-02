package de.tudortmund.wt2.luminary.repository;

import de.tudortmund.wt2.luminary.entity.SparkDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SparkRepository extends JpaRepository<SparkDAO, UUID> {}