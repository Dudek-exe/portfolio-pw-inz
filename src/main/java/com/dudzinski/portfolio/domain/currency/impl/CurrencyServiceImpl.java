package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.currency.mapper.CurrencyMapper;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import com.dudzinski.portfolio.domain.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    public void createNewCurrency(String name,
                                  String rate,
                                  String code,
                                  LocalDate date) {
        CurrencyEntity newCurrencyEntity = new CurrencyEntity(name, new BigDecimal(rate), code, date);
        currencyRepository.save(newCurrencyEntity);
    }

    @Override
    public Page<CurrencyResponseDTO> findAll(CurrencySearchParamsDTO currencySearchParamsDTO) {

        if (Objects.isNull(currencySearchParamsDTO.getName()) && Objects.isNull(currencySearchParamsDTO.getCode())) {
            return currencyRepository.findAll(currencySearchParamsDTO.getPageable()).map(currencyMapper::toCurrencyResponseDTO);
        }

        if (Objects.isNull(currencySearchParamsDTO.getName())) {
            return findByCodeContainsIgnoreCase(currencySearchParamsDTO.getCode(), currencySearchParamsDTO.getPageable());
        }
        if (Objects.isNull(currencySearchParamsDTO.getCode())) {
            return findAllByNameContainsIgnoreCase(currencySearchParamsDTO.getName(), currencySearchParamsDTO.getPageable());
        }
        return findAllByNameAndCodeContainsIgnoreCase(currencySearchParamsDTO.getName(), currencySearchParamsDTO.getCode(), currencySearchParamsDTO.getPageable());
    }

    @Override
    public void updateCurrency(String name, Double rate, String code, LocalDate date) {

        Optional<CurrencyEntity> optionalCurrency = currencyRepository.findByCodeIgnoreCase(code, date);

        if (optionalCurrency.isPresent()) {
            CurrencyEntity currencyEntity = optionalCurrency.get();
            currencyEntity.setRate(BigDecimal.valueOf(rate));
            currencyRepository.save(currencyEntity);
        } else {
            CurrencyEntity newCurrencyEntity = new CurrencyEntity(name, BigDecimal.valueOf(rate), code, date);
            currencyRepository.save(newCurrencyEntity);
        }
    }

    private Page<CurrencyResponseDTO> findByCodeContainsIgnoreCase(String code, Pageable pageable) {
        return currencyRepository.findAllByCodeContainsIgnoreCase(code, pageable).map(currencyMapper::toCurrencyResponseDTO);
    }


    private Page<CurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name, Pageable pageable) {
        return currencyRepository.findByNameContainsIgnoreCase(name, pageable).map(currencyMapper::toCurrencyResponseDTO);
    }

    private Page<CurrencyResponseDTO> findAllByNameAndCodeContainsIgnoreCase(String name, String code, Pageable pageable) {
        return currencyRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code, pageable)
                .map(currencyMapper::toCurrencyResponseDTO);
    }
}
