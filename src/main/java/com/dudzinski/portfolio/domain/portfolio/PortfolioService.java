package com.dudzinski.portfolio.domain.portfolio;

import com.dudzinski.portfolio.application.portfolio.dto.PortfolioPersistDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchParamsDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchResultDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioUpdateDTO;
import org.springframework.data.domain.Page;

public interface PortfolioService {

    PortfolioEntity persist(PortfolioPersistDTO dto);

    Page<PortfolioSearchResultDTO> search(PortfolioSearchParamsDTO dto);

    PortfolioSearchResultDTO getById(Long portfolioId);

    PortfolioSearchResultDTO update(Long portfolioId, PortfolioUpdateDTO dto);

    void delete(Long portfolioId);
}
