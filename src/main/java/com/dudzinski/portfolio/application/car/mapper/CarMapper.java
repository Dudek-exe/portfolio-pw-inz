package com.dudzinski.portfolio.application.car.mapper;

import com.dudzinski.portfolio.application.car.dto.CarPersistDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchResultDTO;
import com.dudzinski.portfolio.domain.car.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarEntity toEntity(CarPersistDTO dto);

    CarSearchResultDTO toCarSearchResultDTO(CarEntity entity);

}
