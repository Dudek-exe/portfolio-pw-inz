package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.application.currency.dto.NewCurrencyResponseDTO;
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

    @Override
    public CurrencyEntity createNewCurrency(String name,
                                            String rate,
                                            String code) {
        CurrencyEntity newCurrencyEntity = new CurrencyEntity(name, new BigDecimal(rate), code);
        return currencyRepository.save(newCurrencyEntity);
    }

    @Override
    public Page<NewCurrencyResponseDTO> findAll(String name, String code, int start, int end) {
        int size = end - start;
        int pageNumber = ((end / size) - 1);

        Pageable pageable = PageRequest.of(pageNumber, size);

        if (Objects.isNull(name) && Objects.isNull(code)) {
            return getAll(pageable);
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
    public Page<NewCurrencyResponseDTO> getAll(Pageable pageable) {
        return currencyRepository.findAll(pageable).map(NewCurrencyResponseDTO::from);
    }

    @Override
    public Page<NewCurrencyResponseDTO> findByCodeContainsIgnoreCase(String code, Pageable pageable) {
        return currencyRepository.findAllByCodeContainsIgnoreCase(code, pageable).map(NewCurrencyResponseDTO::from);
    }

    @Override
    public Page<NewCurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name, Pageable pageable) {
        return currencyRepository.findByNameContainsIgnoreCase(name, pageable).map(NewCurrencyResponseDTO::from);
    }

    @Override
    public Page<NewCurrencyResponseDTO> findAllByNameAndCodeContainsIgnoreCase(String name, String code, Pageable pageable) {
        return currencyRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code, pageable).map(NewCurrencyResponseDTO::from);
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
