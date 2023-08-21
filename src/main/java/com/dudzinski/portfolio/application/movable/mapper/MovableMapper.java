package com.dudzinski.portfolio.application.movable.mapper;

import com.dudzinski.portfolio.application.movable.dto.MovablePersistDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchResultDTO;
import com.dudzinski.portfolio.domain.movable.MovableEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovableMapper {

    MovableEntity toEntity(MovablePersistDTO dto);

    MovableSearchResultDTO toMovableSearchResultDTO(MovableEntity entity);

}
