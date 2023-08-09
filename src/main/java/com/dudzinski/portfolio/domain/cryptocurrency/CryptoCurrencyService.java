package com.dudzinski.portfolio.domain.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface CryptoCurrencyService {

    Page<CryptoCurrencyResponseDTO> findAll(CryptoCurrencySearchParamsDTO searchParamsDTO);

    void createNewCryptoCurrency(String name, BigDecimal rate, String code);

    void saveFromExternalApi(CryptoCurrencyEntity c);
}
