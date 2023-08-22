package com.dudzinski.portfolio.application.property.mapper;

import com.dudzinski.portfolio.application.property.dto.PropertyPersistDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchResultDTO;
import com.dudzinski.portfolio.domain.property.PropertyEntity;
import com.dudzinski.portfolio.domain.property.PropertyType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(target = "type", source = "propertyType", qualifiedByName = "toEnumType")
    PropertyEntity toEntity(PropertyPersistDTO dto);

    @Mapping(target = "purchaseDate", source = "purchaseDate", qualifiedByName = "toStringDate")
    @Mapping(target = "propertyType", source = "type", qualifiedByName = "toStringType")
    PropertySearchResultDTO toPropertySearchResultDTO(PropertyEntity entity);

    @Named("toEnumType")
    default PropertyType toEnumType(String type) {
        return PropertyType.from(type);
    }

    @Named("toStringType")
    default String toStringType(PropertyType type) {
        return type.getDescription();
    }

    @Named("toStringDate")
    default String toStringDate(LocalDate localDate) {
        return localDate.toString();
    }
}
