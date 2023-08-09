package com.dudzinski.portfolio.application.currency.mapper;

import com.dudzinski.portfolio.application.currency.dto.CurrencyResponseDTO;
import com.dudzinski.portfolio.domain.currency.CurrencyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyResponseDTO toCurrencyResponseDTO(CurrencyEntity cryptoCurrencyEntity);

}
