package com.dudzinski.portfolio.domain.currency.impl;

import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchResultDTO;
import com.dudzinski.portfolio.application.currency.mapper.CurrencyMapper;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import com.dudzinski.portfolio.domain.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    public Page<CurrencySearchResultDTO> search(CurrencySearchParamsDTO searchParamsDTO) {

        return currencyRepository.findAll(
                        currencyRepository.buildSpecification(searchParamsDTO),
                        searchParamsDTO.getPageable()
                )
                .map(currencyMapper::toCurrencySearchResultDTO);
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
}
