package com.dudzinski.portfolio.domain.currency;

import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurrencyService {

    CurrencyEntity createNewCurrency(String name, String rate, String code);

    void updateCurrency(String name, Double rate, String code);

    Page<CurrencyResponseDTO> findAll(String name, String code, int start, int end);

    Page<CurrencyResponseDTO> findByCodeContainsIgnoreCase(String code, Pageable pageable);

    Page<CurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<CurrencyResponseDTO> findAllByNameAndCodeContainsIgnoreCase(String name, String code, Pageable pageable);
}


