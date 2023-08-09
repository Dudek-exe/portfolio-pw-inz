package com.dudzinski.portfolio.application.cryptocurrency.mapper;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyResponseDTO;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaCryptoCurrency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public interface CryptoCurrencyMapper {

    String USD_INDEX = "USD";

    @Mapping(target = "rate", source = ".", qualifiedByName = "toCryptoCurrencyRate")
    @Mapping(target = "code", source = "symbol")
    CryptoCurrencyEntity toEntity(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency);

    CryptoCurrencyResponseDTO toCryptoCurrencyResponseDTO(CryptoCurrencyEntity cryptoCurrencyEntity);

    @Named("toCryptoCurrencyRate")
    default BigDecimal toCryptoCurrencyRate(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return BigDecimal.valueOf(coinPapricaCryptoCurrency.getQuotes()
                .get(USD_INDEX)
                .getPrice()).setScale(8, RoundingMode.HALF_UP);
    }
}
