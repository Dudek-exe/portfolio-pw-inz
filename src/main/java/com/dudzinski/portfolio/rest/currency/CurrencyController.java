package com.dudzinski.portfolio.rest.currency;

import com.dudzinski.portfolio.application.currency.CurrencyFacade;
import com.dudzinski.portfolio.application.currency.dto.NewCurrencyRequestDTO;
import com.dudzinski.portfolio.application.currency.dto.NewCurrencyResponseDTO;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import com.dudzinski.portfolio.rest.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ControllerConstants.RESOURCE_CURRENCY)
class CurrencyController {

    final CurrencyFacade currencyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<NewCurrencyResponseDTO> getAll(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String code,
                                        @RequestParam(name = "_start") int start,
                                        @RequestParam(name = "_end") int end) {
        return currencyFacade.findAll(name, code, start, end);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    CurrencyEntity save(@RequestBody NewCurrencyRequestDTO newCurrencyRequestDTO) {
        return currencyFacade.createNewCurrency(newCurrencyRequestDTO);
    }

}
