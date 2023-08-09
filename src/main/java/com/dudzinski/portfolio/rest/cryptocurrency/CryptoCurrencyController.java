package com.dudzinski.portfolio.rest.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.CryptoCurrencyFacade;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.NewCryptoCurrencyRequestDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "cryptoCurrency")
class CryptoCurrencyController {

    private final CryptoCurrencyFacade cryptoCurrencyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<CryptoCurrencyResponseDTO> getAll(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String code,
                                           @RequestParam(name = "_start") int start,
                                           @RequestParam(name = "_end") int end,
                                           @RequestParam(name = "_order") String order,
                                           @RequestParam(name = "_sort") String sort) {
        return cryptoCurrencyFacade.findAll(wrapSearchParams(name, code, start, end, order, sort));
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    ResponseEntity<Void> save(@RequestBody NewCryptoCurrencyRequestDTO cryptoCurrencyRequestDTO) {
        cryptoCurrencyFacade.createNewCryptoCurrency(cryptoCurrencyRequestDTO);
        return ResponseEntity.noContent().build();
    }

    private CryptoCurrencySearchParamsDTO wrapSearchParams(String name, String code, int start, int end, String order, String sort) {
        return new CryptoCurrencySearchParamsDTO(
                name,
                code,
                start,
                end,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
