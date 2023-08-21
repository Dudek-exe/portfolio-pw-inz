package com.dudzinski.portfolio.application.currency;

import com.dudzinski.portfolio.application.currency.dto.CurrencyDetailsResultDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.currency.dto.CurrencySearchResultDTO;
import com.dudzinski.portfolio.domain.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyFacade {

    private final CurrencyService currencyServiceImpl;

    public Page<CurrencySearchResultDTO> search(CurrencySearchParamsDTO currencySearchParamsDTO) {
        return currencyServiceImpl.search(currencySearchParamsDTO);
    }

    public List<CurrencyDetailsResultDTO> getDetails(CurrencySearchParamsDTO searchParamsDTO) {
        return null;//FIXME currencyServiceImpl.getDetails(searchParamsDTO);
    }
}
