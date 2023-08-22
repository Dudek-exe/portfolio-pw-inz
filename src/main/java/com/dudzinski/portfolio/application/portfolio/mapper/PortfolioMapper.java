package com.dudzinski.portfolio.application.portfolio.mapper;

import com.dudzinski.portfolio.application.portfolio.dto.PortfolioPersistDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchResultDTO;
import com.dudzinski.portfolio.domain.portfolio.PortfolioEntity;
import com.dudzinski.portfolio.domain.portfolio.PortfolioType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "toEnumType")
    PortfolioEntity toEntity(PortfolioPersistDTO dto);

    @Mapping(target = "type", source = "type", qualifiedByName = "toStringType")
    PortfolioSearchResultDTO toPortfolioSearchResultDTO(PortfolioEntity entity);

    @Named("toEnumType")
    default PortfolioType toEnumType(String type) {
        return PortfolioType.from(type);
    }

    @Named("toStringType")
    default String toStringType(PortfolioType type) {
        return type.getDescription();
    }

    @Named("toStringDate")
    default String toStringDate(LocalDate localDate) {
        return localDate.toString();
    }
}
