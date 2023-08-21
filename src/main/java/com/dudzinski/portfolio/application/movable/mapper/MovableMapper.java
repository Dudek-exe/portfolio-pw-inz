package com.dudzinski.portfolio.application.movable.mapper;

import com.dudzinski.portfolio.application.movable.dto.MovablePersistDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchResultDTO;
import com.dudzinski.portfolio.domain.movable.MovableEntity;
import com.dudzinski.portfolio.domain.movable.MovableType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovableMapper {

    @Mapping(target = "type", source = "movableType", qualifiedByName = "toEnumType")
    MovableEntity toEntity(MovablePersistDTO dto);

    @Mapping(target = "movableType", source = "type", qualifiedByName = "toStringType")
    MovableSearchResultDTO toMovableSearchResultDTO(MovableEntity entity);

    @Named("toEnumType")
    default MovableType toEnumType(String type) {
        return MovableType.from(type);
    }

    @Named("toStringType")
    default String toStringType(MovableType type) {
        return type.getDescription();
    }

}
