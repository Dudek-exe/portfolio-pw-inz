package com.dudzinski.portfolio.application.cryptocurrency.mapper;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsResultDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchResultDTO;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyEntity;
import com.dudzinski.portfolio.infrastructure.external.coinpaprica.CoinPapricaCryptoCurrency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface CryptoCurrencyMapper {

    String USD_INDEX = "USD";

    @Mapping(target = "rate", source = ".", qualifiedByName = "toCryptoCurrencyRate")
    @Mapping(target = "date", source = ".", qualifiedByName = "toCryptoCurrencyDate")
    @Mapping(target = "code", source = "symbol")
    @Mapping(target = "pc15m", source = ".", qualifiedByName = "toCryptoPercentChange15m")
    @Mapping(target = "pc30m", source = ".", qualifiedByName = "toCryptoPercentChange30m")
    @Mapping(target = "pc1h", source = ".", qualifiedByName = "toCryptoPercentChange1h")
    @Mapping(target = "pc6h", source = ".", qualifiedByName = "toCryptoPercentChange6h")
    @Mapping(target = "pc12h", source = ".", qualifiedByName = "toCryptoPercentChange12h")
    @Mapping(target = "pc1d", source = ".", qualifiedByName = "toCryptoPercentChange1d")
    CryptoCurrencyEntity toEntity(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency);

    @Mapping(target = "date", source = "date", qualifiedByName = "toStringDate")
    CryptoCurrencySearchResultDTO toCryptoCurrencySearchResultDTO(CryptoCurrencyEntity cryptoCurrencyEntity);

    @Mapping(target = "date", source = "date", qualifiedByName = "toStringDate")
    CryptoCurrencyDetailsResultDTO toCryptoCurrencyDetailsResultDTO(CryptoCurrencyEntity cryptoCurrencyEntity);

    @Named("toCryptoCurrencyRate")
    default BigDecimal toCryptoCurrencyRate(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return BigDecimal.valueOf(coinPapricaCryptoCurrency.getQuotes()
                        .get(USD_INDEX)
                        .getPrice())
                .setScale(8, RoundingMode.HALF_UP);
    }

    @Named("toCryptoCurrencyDate")
    default LocalDateTime toCryptoCurrencyDate(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        String stringDate = coinPapricaCryptoCurrency
                .getLast_updated()
                .replace("Z", "");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(stringDate, formatter);
    }

    @Named("toStringDate")
    default String toStringDate(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }

    @Named("toCryptoPercentChange15m")
    default Double toCryptoPercentChange15m(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return coinPapricaCryptoCurrency.getQuotes()
                .get(USD_INDEX)
                .getPercent_change_15m();
    }

    @Named("toCryptoPercentChange30m")
    default Double toCryptoPercentChange30m(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return coinPapricaCryptoCurrency.getQuotes()
                .get(USD_INDEX)
                .getPercent_change_30m();
    }

    @Named("toCryptoPercentChange1h")
    default Double toCryptoPercentChange1h(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return coinPapricaCryptoCurrency.getQuotes()
                .get(USD_INDEX)
                .getPercent_change_1h();
    }

    @Named("toCryptoPercentChange6h")
    default Double toCryptoPercentChange6h(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return coinPapricaCryptoCurrency.getQuotes()
                .get(USD_INDEX)
                .getPercent_change_6h();
    }

    @Named("toCryptoPercentChange12h")
    default Double toCryptoPercentChange12h(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return coinPapricaCryptoCurrency.getQuotes()
                .get(USD_INDEX)
                .getPercent_change_12h();
    }

    @Named("toCryptoPercentChange1d")
    default Double toCryptoPercentChange1d(CoinPapricaCryptoCurrency coinPapricaCryptoCurrency) {
        return coinPapricaCryptoCurrency.getQuotes()
                .get(USD_INDEX)
                .getPercent_change_24h();
    }
}
