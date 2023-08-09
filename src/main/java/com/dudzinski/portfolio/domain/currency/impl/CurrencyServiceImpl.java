package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import com.dudzinski.portfolio.application.currency.mapper.CurrencyMapper;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import com.dudzinski.portfolio.domain.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    public CurrencyEntity createNewCurrency(String name,
                                            String rate,
                                            String code) {
        CurrencyEntity newCurrencyEntity = new CurrencyEntity(name, new BigDecimal(rate), code);
        return currencyRepository.save(newCurrencyEntity);
    }

    @Override
    public Page<CurrencyResponseDTO> findAll(String name, String code, int start, int end) {
        int size = end - start;
        int pageNumber = ((end / size) - 1);

        Pageable pageable = PageRequest.of(pageNumber, size);

        if (Objects.isNull(name) && Objects.isNull(code)) {
            return currencyRepository.findAll(pageable).map(currencyMapper::toCurrencyResponseDTO);
        }

        if (Objects.isNull(name)) {
            return findByCodeContainsIgnoreCase(code, pageable);
        }
        if (Objects.isNull(code)) {
            return findAllByNameContainsIgnoreCase(name, pageable);
        }
        return findAllByNameAndCodeContainsIgnoreCase(name, code, pageable);
    }

    @Override
    public Page<CurrencyResponseDTO> findByCodeContainsIgnoreCase(String code, Pageable pageable) {
        return currencyRepository.findAllByCodeContainsIgnoreCase(code, pageable).map(currencyMapper::toCurrencyResponseDTO);
    }

    @Override
    public Page<CurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name, Pageable pageable) {
        return currencyRepository.findByNameContainsIgnoreCase(name, pageable).map(currencyMapper::toCurrencyResponseDTO);
    }

    @Override
    public Page<CurrencyResponseDTO> findAllByNameAndCodeContainsIgnoreCase(String name, String code, Pageable pageable) {
        return currencyRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code, pageable)
                .map(currencyMapper::toCurrencyResponseDTO);
    }

    @Override
    public void updateCurrency(String name, Double rate, String code) {

        Optional<CurrencyEntity> optionalCurrency = currencyRepository.findByCodeIgnoreCase(code);

        if (optionalCurrency.isPresent()) {
            CurrencyEntity currencyEntity = optionalCurrency.get();
            currencyEntity.setRate(BigDecimal.valueOf(rate));
            currencyRepository.save(currencyEntity);
        } else {
            CurrencyEntity newCurrencyEntity = new CurrencyEntity(name, BigDecimal.valueOf(rate), code);
            currencyRepository.save(newCurrencyEntity);
        }
    }

}
