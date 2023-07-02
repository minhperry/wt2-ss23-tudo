package de.tudortmund.wt2.luminary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Table(name = "sparks_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparkDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @NotNull
    @Size(max = 500)
    private String content;
    @NotNull
    private int lightCount;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    @NotNull
    private UserDAO creator;
    @NotNull
    private Timestamp createdAt;
    private Timestamp lastEditedAt;
}