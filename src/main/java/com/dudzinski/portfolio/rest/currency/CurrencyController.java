package com.dudzinski.portfolio.rest.currency;

import com.dudzinski.portfolio.application.currency.CurrencyFacade;
import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.currency.dto.NewCurrencyRequestDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CurrencyControllerConstants.RESOURCE_CURRENCY)
class CurrencyController {

    final CurrencyFacade currencyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<CurrencyResponseDTO> getAll(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String code,
                                     @RequestParam(name = "_start") int start,
                                     @RequestParam(name = "_end") int end,
                                     @RequestParam(name = "_order") String order,
                                     @RequestParam(name = "_sort") String sort) {

        return currencyFacade.search(wrapSearchParams(name, code, start, end, order, sort)
        );
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    ResponseEntity<Void> save(@RequestBody NewCurrencyRequestDTO newCurrencyRequestDTO) {
        currencyFacade.createNewCurrency(newCurrencyRequestDTO);
        return ResponseEntity.noContent().build();
    }

    private CurrencySearchParamsDTO wrapSearchParams(String name, String code, int start, int end, String order, String sort) {
        return new CurrencySearchParamsDTO(
                name,
                code,
                start,
                end,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
