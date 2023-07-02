package de.tudortmund.wt2.luminary.model.spark;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.tudortmund.wt2.luminary.model.auth.UserContentInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparkDto {
    private UUID id;
    private String content;
    private int lightCount;
    private UserContentInfoDto creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastEditedAt;

}