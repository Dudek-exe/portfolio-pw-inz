package com.dudzinski.portfolio.domain.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CryptoCurrencyService {

    List<CryptoCurrencyResponseDTO> findAll(String name, String code);

    void createNewCryptoCurrency(String name, BigDecimal rate, String code);

    void saveFromExternalApi(CryptoCurrencyEntity c);

}
