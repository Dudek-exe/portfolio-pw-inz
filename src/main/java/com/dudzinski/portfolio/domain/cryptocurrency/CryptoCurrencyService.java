package com.dudzinski.portfolio.domain.cryptocurrency;

import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencyDetailsResultDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchParamsDTO;
import com.dudzinski.portfolio.application.cryptocurrency.dto.CryptoCurrencySearchResultDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CryptoCurrencyService {

    Page<CryptoCurrencySearchResultDTO> search(CryptoCurrencySearchParamsDTO searchParamsDTO);

    void saveFromExternalApi(CryptoCurrencyEntity c);

    List<CryptoCurrencyDetailsResultDTO> getDetails(CryptoCurrencyDetailsParamsDTO searchParamsDTO);
}
