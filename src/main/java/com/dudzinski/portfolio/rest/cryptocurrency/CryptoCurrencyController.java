package com.dudzinski.portfolio.rest.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.CryptoCurrencyFacade;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.NewCryptoCurrencyRequestDTO;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "cryptoCurrency")
class CryptoCurrencyController {

    private final CryptoCurrencyFacade cryptoCurrencyFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    List<CryptoCurrencyResponseDTO> getAll(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String code) {
        return cryptoCurrencyFacade.findAll(name, code);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    CryptoCurrencyEntity save(@RequestBody NewCryptoCurrencyRequestDTO cryptoCurrencyRequestDTO) {
        return cryptoCurrencyFacade.createNewCryptoCurrency(cryptoCurrencyRequestDTO);
    }

}
