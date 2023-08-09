package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.mapper.CryptoCurrencyMapper;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Override
    public Page<CryptoCurrencyResponseDTO> findAll(CryptoCurrencySearchParamsDTO searchParamsDTO) {

        if (Objects.isNull(searchParamsDTO.getName()) && Objects.isNull(searchParamsDTO.getCode())) {
            return cryptoCurrencyRepository.findAll(searchParamsDTO.getPageable())
                    .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO);

        }
        if (Objects.isNull(searchParamsDTO.getName())) {
            return findAllByCodeContainsIgnoreCase(searchParamsDTO.getCode(), searchParamsDTO.getPageable());
        }
        if (Objects.isNull(searchParamsDTO.getCode())) {
            return findAllByNameContainsIgnoreCase(searchParamsDTO.getName(), searchParamsDTO.getPageable());
        }
        return findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(
                searchParamsDTO.getName(),
                searchParamsDTO.getCode(),
                searchParamsDTO.getPageable()
        );
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

    private Page<CryptoCurrencyResponseDTO> findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(String name, String code, Pageable pageable) {
        return cryptoCurrencyRepository.findAllByNameContainsIgnoreCaseAndCodeContainsIgnoreCase(name, code, pageable)
                .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO);
    }

    private Page<CryptoCurrencyResponseDTO> findAllByNameContainsIgnoreCase(String name, Pageable pageable) {
        return cryptoCurrencyRepository.findAllByNameContainsIgnoreCase(name, pageable)
                .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO);
    }

    private Page<CryptoCurrencyResponseDTO> findAllByCodeContainsIgnoreCase(String code, Pageable pageable) {
        return cryptoCurrencyRepository.findAllByCodeContainsIgnoreCase(code, pageable)
                .map(cryptoCurrencyMapper::toCryptoCurrencyResponseDTO);
    }
}
