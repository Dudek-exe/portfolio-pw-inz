package com.dudzinski.portfolio.domain.currency;

import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import org.springframework.data.domain.Page;

public interface CurrencyService {

    void createNewCurrency(String name, String rate, String code);

    void updateCurrency(String name, Double rate, String code);

    Page<CurrencyResponseDTO> findAll(String name, String code, int start, int end);
}


