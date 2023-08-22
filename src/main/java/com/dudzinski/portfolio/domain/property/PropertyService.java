package com.dudzinski.portfolio.domain.property;

import com.dudzinski.portfolio.application.property.dto.PropertyPersistDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchParamsDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchResultDTO;
import com.dudzinski.portfolio.application.property.dto.PropertyUpdateDTO;
import org.springframework.data.domain.Page;

public interface PropertyService {

    PropertyEntity persist(PropertyPersistDTO dto);

    Page<PropertySearchResultDTO> search(PropertySearchParamsDTO dto);

    PropertySearchResultDTO getById(Long propertyId);

    PropertySearchResultDTO update(Long propertyId, PropertyUpdateDTO dto);

    void delete(Long propertyId);
}
