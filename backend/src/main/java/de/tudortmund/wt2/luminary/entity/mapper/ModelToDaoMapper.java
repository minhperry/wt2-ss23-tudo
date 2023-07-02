package de.tudortmund.wt2.luminary.entity.mapper;

import de.tudortmund.wt2.luminary.entity.SparkDAO;
import de.tudortmund.wt2.luminary.entity.UserDAO;
import de.tudortmund.wt2.luminary.model.auth.RegisterDto;
import de.tudortmund.wt2.luminary.model.spark.SparkDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper
public interface ModelToDaoMapper {
    SparkDAO map(SparkDto sparkDto);

    SparkDAO update(@MappingTarget SparkDAO target, String update);

    SparkDAO update(@MappingTarget SparkDAO target, Integer lightCount);

    UserDAO map (RegisterDto registerDto);

    default Timestamp mapLocalDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }
}