package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.application.cryptocurrency.mapper.CryptoCurrencyMapper;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Override
    public List<CryptoCurrencyResponseDTO> findAll(String name, String code) {

        if (Objects.isNull(name) && Objects.isNull(code)) {
            return getAll();
        }
        if (Objects.isNull(name)) {
            return findAllByCodeContainsIgnoreCase(code);
        }
        if (Objects.isNull(code)) {
            return findAllByNameContainsIgnoreCase(name);
        }
        return findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code);
    }

    @Override
    public void createNewCryptoCurrency(String name, BigDecimal rate, String code) {
        CryptoCurrencyEntity cryptoCurrencyEntity = new CryptoCurrencyEntity(name, rate, code);
        cryptoCurrencyRepository.save(cryptoCurrencyEntity);
    }

    @Override
    public void saveFromExternalApi(CryptoCurrencyEntity cryptoCurrency) {
        cryptoCurrencyRepository.save(cryptoCurrency);
    }

    private List<CryptoCurrencyResponseDTO> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code) {
        return cryptoCurrencyRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code).stream()
                .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO)
                .toList();
    }

    private List<CryptoCurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name) {
        return cryptoCurrencyRepository.findAllByNameContainsIgnoreCase(name).stream()
                .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO)
                .toList();
    }

    private List<CryptoCurrencyResponseDTO> findAllByCodeContainsIgnoreCase(String code) {
        return cryptoCurrencyRepository.findAllByCodeContainsIgnoreCase(code).stream()
                .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO)
                .toList();
    }

    private List<CryptoCurrencyResponseDTO> getAll() {
        return cryptoCurrencyRepository.findAll().stream()
                .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO)
                .toList();
    }
}
