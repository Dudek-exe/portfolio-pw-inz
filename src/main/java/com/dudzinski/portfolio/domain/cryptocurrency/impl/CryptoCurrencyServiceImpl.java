package com.dudzinski.portfolio.domain.cryptocurrency.impl;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsResultDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchResultDTO;
import com.dudzinski.portfolio.application.cryptocurrency.mapper.CryptoCurrencyMapper;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public Page<CryptoCurrencySearchResultDTO> search(CryptoCurrencySearchParamsDTO searchParamsDTO) {

        return cryptoCurrencyRepository.findAll(
                        cryptoCurrencyRepository.buildSpecification(searchParamsDTO),
                        searchParamsDTO.getPageable()
                )
                .map(cryptoCurrencyMapper::toCryptoCurrencySearchResultDTO);
    }

    @Override
    public void saveFromExternalApi(CryptoCurrencyEntity cryptoCurrency) {
        cryptoCurrencyRepository.save(cryptoCurrency);
    }

    @Override
    public List<CryptoCurrencyDetailsResultDTO> getDetails(CryptoCurrencyDetailsParamsDTO searchParamsDTO) {
        List<CryptoCurrencyEntity> cryptoCurrencies = cryptoCurrencyRepository.findAll(cryptoCurrencyRepository.buildSpecification(searchParamsDTO))
                .stream()
                .sorted(Comparator.comparing(CryptoCurrencyEntity::getDate).reversed())
                .filter(distinctByKey(CryptoCurrencyEntity::getName))
                .toList();

        return cryptoCurrencies.stream()
                .map(cryptoCurrencyMapper::toCryptoCurrencyDetailsResultDTO)
                .toList();
    }
}
