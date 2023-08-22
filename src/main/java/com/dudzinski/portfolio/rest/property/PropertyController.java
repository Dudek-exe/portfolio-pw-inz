package com.dudzinski.portfolio.rest.property;

import com.dudzinski.portfolio.application.property.PropertyFacade;
import com.dudzinski.portfolio.application.property.dto.PropertyPersistDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchParamsDTO;
import com.dudzinski.portfolio.application.property.dto.PropertySearchResultDTO;
import com.dudzinski.portfolio.application.property.dto.PropertyUpdateDTO;
import com.dudzinski.portfolio.infrastructure.util.HTTPVoidWrapperDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = PropertyControllerConstants.RESOURCE_CURRENCY)
public class PropertyController {

    private final PropertyFacade propertyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<PropertySearchResultDTO> search(@RequestParam(required = false) String name,
                                         @RequestParam(name = "_start") int start,
                                         @RequestParam(name = "_end") int end,
                                         @RequestParam(name = "_order") String order,
                                         @RequestParam(name = "_sort") String sort) {
        return propertyFacade.search(wrapSearchParams(
                        name,
                        start,
                        end,
                        order,
                        sort
                )
        );
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping("/{propertyId}")
    PropertySearchResultDTO get(@PathVariable Long propertyId) {
        return propertyFacade.getById(propertyId);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PutMapping("/{propertyId}")
    PropertySearchResultDTO update(@PathVariable Long propertyId,
                                   @RequestBody PropertyUpdateDTO dto) {
        return propertyFacade.update(propertyId, dto);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @DeleteMapping("/{propertyId}")
    ResponseEntity<HTTPVoidWrapperDTO> delete(@PathVariable Long propertyId) {
        propertyFacade.delete(propertyId);
        return ResponseEntity.ok(new HTTPVoidWrapperDTO("Success"));
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    ResponseEntity<HTTPVoidWrapperDTO> save(@RequestBody PropertyPersistDTO dto) {
        propertyFacade.persist(dto);
        return ResponseEntity.ok(new HTTPVoidWrapperDTO("Success"));
    }

    private PropertySearchParamsDTO wrapSearchParams(
            String name,
            int start,
            int end,
            String order,
            String sort) {
        return new PropertySearchParamsDTO(
                name,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
