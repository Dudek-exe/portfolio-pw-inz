package com.dudzinski.portfolio.application.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsResultDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchResultDTO;
import com.dudzinski.portfolio.domain.cryptocurrency.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyFacade {

    private final CryptoCurrencyService cryptoCurrencyServiceImpl;

    public Page<CryptoCurrencySearchResultDTO> search(CryptoCurrencySearchParamsDTO searchParamsDTO) {
        return cryptoCurrencyServiceImpl.search(searchParamsDTO);
    }

    public List<CryptoCurrencyDetailsResultDTO> getDetails(CryptoCurrencyDetailsParamsDTO searchParamsDTO) {
        return cryptoCurrencyServiceImpl.getDetails(searchParamsDTO);
    }
}
