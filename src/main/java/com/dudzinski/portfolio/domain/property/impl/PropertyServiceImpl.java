package com.dudzinski.portfolio.domain.property.impl;

import com.dudzinski.portfolio.application.property.dto.PropertyPersistDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchParamsDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchResultDTO;
import com.dudzinski.portfolio.application.property.dto.PropertyUpdateDTO;
import com.dudzinski.portfolio.application.property.mapper.PropertyMapper;
import com.dudzinski.portfolio.domain.property.PropertyEntity;
import com.dudzinski.portfolio.domain.property.PropertyService;
import com.dudzinski.portfolio.domain.property.PropertyType;
import com.dudzinski.portfolio.domain.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public PropertyEntity persist(PropertyPersistDTO dto) {
        PropertyEntity property = propertyMapper.toEntity(dto);
        property.setUsernameOwner(SecurityUtils.getLoggedUserLogin());
        return propertyRepository.save(property);
    }

    @Override
    public Page<PropertySearchResultDTO> search(PropertySearchParamsDTO searchParamsDTO) {

        return propertyRepository.findAll(
                        propertyRepository.buildSpecification(searchParamsDTO),
                        searchParamsDTO.getPageable()
                )
                .map(propertyMapper::toPropertySearchResultDTO);
    }

    @Override
    public PropertySearchResultDTO getById(Long propertyId) {
        return propertyMapper.toPropertySearchResultDTO(propertyRepository.getById(propertyId));
    }

    @Override
    public PropertySearchResultDTO update(Long propertyId, PropertyUpdateDTO dto) {
        PropertyEntity property = propertyRepository.getById(propertyId);
        property.setName(dto.getName());
        property.setAddress(dto.getAddress());
        property.setArea(dto.getArea());
        property.setPurchasePrice(dto.getPurchasePrice());
        property.setPurchaseDate(dto.getPurchaseDate());
        property.setEstimatedValue(dto.getEstimatedValue());
        property.setType(PropertyType.from(dto.getPropertyType()));

        return propertyMapper.toPropertySearchResultDTO(propertyRepository.save(property));
    }

    @Override
    public void delete(Long propertyId) {
        propertyRepository.delete(propertyId);
    }
}
