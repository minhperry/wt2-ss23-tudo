package de.tudortmund.wt2.luminary.repository;

import de.tudortmund.wt2.luminary.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    Optional<UserDAO> findByUsername(@NonNull String username);

    boolean existsByUsername(@NonNull String username);
}