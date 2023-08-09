package com.dudzinski.portfolio.domain.currency;

import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface CurrencyService {

    void createNewCurrency(String name, String rate, String code, LocalDate date);

    void updateCurrency(String name, Double rate, String code, LocalDate date);

    Page<CurrencyResponseDTO> findAll(CurrencySearchParamsDTO currencySearchParamsDTO);
}


