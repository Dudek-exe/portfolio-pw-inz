package com.dudzinski.portfolio.rest.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.CryptoCurrencyFacade;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchResultDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.dudzinski.portfolio.rest.cryptocurrency.CryptoCurrencyControllerConstants.SEARCH_RESOURCE_CRYPTO_CURRENCY;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = SEARCH_RESOURCE_CRYPTO_CURRENCY)
class CryptoCurrencySearchController {

    private final CryptoCurrencyFacade cryptoCurrencyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<CryptoCurrencySearchResultDTO> search(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) String code,
                                               @RequestParam(name = "_start") int start,
                                               @RequestParam(name = "_end") int end,
                                               @RequestParam(name = "_order") String order,
                                               @RequestParam(name = "_sort") String sort,
                                               @RequestParam(required = false) LocalDate dateFrom,
                                               @RequestParam(required = false) LocalDate dateTo) {
        return cryptoCurrencyFacade.search(
                wrapSearchParams(
                        name,
                        code,
                        start,
                        end,
                        order,
                        sort,
                        dateFrom,
                        dateTo
                )
        );
    }

    private CryptoCurrencySearchParamsDTO wrapSearchParams(
            String name,
            String code,
            int start,
            int end,
            String order,
            String sort,
            LocalDate dateFrom,
            LocalDate dateTo) {
        return new CryptoCurrencySearchParamsDTO(
                name,
                code,
                dateFrom,
                dateTo,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
