package de.tudortmund.wt2.luminary.entity.mapper;

import de.tudortmund.wt2.luminary.entity.SparkDAO;
import de.tudortmund.wt2.luminary.entity.UserDAO;
import de.tudortmund.wt2.luminary.model.spark.SparkDto;
import de.tudortmund.wt2.luminary.model.auth.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface DaoToModelMapper {
    SparkDto map(SparkDAO sparkDAO);

    UserDto map(UserDAO userDAO);
}
