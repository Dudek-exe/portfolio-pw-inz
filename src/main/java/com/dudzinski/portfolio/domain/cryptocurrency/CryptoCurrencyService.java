package com.dudzinski.portfolio.domain.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CryptoCurrencyService {

    List<CryptoCurrencyResponseDTO> findAll(String name, String code);

    CryptoCurrencyEntity createNewCryptoCurrency(String name, BigDecimal rate, String code);

    void saveFromExternalApi(CryptoCurrencyEntity c);

    List<CryptoCurrencyResponseDTO> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code);

    List<CryptoCurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name);

    List<CryptoCurrencyResponseDTO> findAllByCodeContainsIgnoreCase(String code);

    List<CryptoCurrencyResponseDTO> getAll();

}
