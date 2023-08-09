package com.dudzinski.portfolio.application.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.NewCryptoCurrencyRequestDTO;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyFacade {

    private final CryptoCurrencyService cryptoCurrencyService;

    public void createNewCryptoCurrency(NewCryptoCurrencyRequestDTO requestDTO) {
        cryptoCurrencyService.createNewCryptoCurrency(requestDTO.getName(), requestDTO.getRate(), requestDTO.getCode());
    }

    public Page<CryptoCurrencyResponseDTO> findAll(CryptoCurrencySearchParamsDTO searchParamsDTO) {
        return cryptoCurrencyService.findAll(searchParamsDTO);
    }

}
