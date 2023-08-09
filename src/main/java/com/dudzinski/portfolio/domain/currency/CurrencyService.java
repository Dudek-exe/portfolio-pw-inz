package com.dudzinski.portfolio.domain.currency;

import com.dudzinski.portfolio.application.currency.dto.NewCurrencyResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurrencyService {

    CurrencyEntity createNewCurrency(String name, String rate, String code);

    void updateCurrency(String name, Double rate, String code);

    Page<NewCurrencyResponseDTO> findAll(String name, String code, int start, int end);

    Page<NewCurrencyResponseDTO> getAll(Pageable pageable);

    Page<NewCurrencyResponseDTO> findByCodeContainsIgnoreCase(String code, Pageable pageable);

    Page<NewCurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<NewCurrencyResponseDTO> findAllByNameAndCodeContainsIgnoreCase(String name, String code, Pageable pageable);
}


