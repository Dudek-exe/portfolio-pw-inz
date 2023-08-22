package com.dudzinski.portfolio.application.property;

import com.dudzinski.portfolio.application.property.dto.PropertyPersistDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchParamsDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchResultDTO;
import com.dudzinski.portfolio.application.property.dto.PropertyUpdateDTO;
import com.dudzinski.portfolio.domain.property.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertyFacade {

    private final PropertyService propertyServiceImpl;

    public void persist(PropertyPersistDTO dto) {
        propertyServiceImpl.persist(dto);
    }

    public Page<PropertySearchResultDTO> search(PropertySearchParamsDTO dto) {
        return propertyServiceImpl.search(dto);
    }

    public PropertySearchResultDTO getById(Long propertyId) {
        return propertyServiceImpl.getById(propertyId);
    }

    public PropertySearchResultDTO update(Long propertyId, PropertyUpdateDTO dto) {
        return propertyServiceImpl.update(propertyId, dto);
    }

    public void delete(Long propertyId) {
        propertyServiceImpl.delete(propertyId);
    }
}
