package com.dudzinski.portfolio.application.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.NewCryptoCurrencyRequestDTO;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyFacade {

    private final CryptoCurrencyService cryptoCurrencyService;

    public List<CryptoCurrencyResponseDTO> findAll(String name, String code) {
        return cryptoCurrencyService.findAll(name, code);
    }

    public CryptoCurrencyEntity createNewCryptoCurrency(NewCryptoCurrencyRequestDTO requestDTO) {
        return cryptoCurrencyService.createNewCryptoCurrency(requestDTO.getName(), requestDTO.getRate(), requestDTO.getCode());
    }
}
