package com.dudzinski.portfolio.application.portfolio;

import com.dudzinski.portfolio.application.portfolio.dto.PortfolioPersistDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchParamsDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchResultDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioUpdateDTO;
import com.dudzinski.portfolio.domain.portfolio.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioFacade {

    private final PortfolioService portfolioService;

    public void persist(PortfolioPersistDTO dto) {
        portfolioService.persist(dto);
    }

    public Page<PortfolioSearchResultDTO> search(PortfolioSearchParamsDTO dto) {
        return portfolioService.search(dto);
    }

    public PortfolioSearchResultDTO getById(Long portfolioId) {
        return portfolioService.getById(portfolioId);
    }

    public PortfolioSearchResultDTO update(Long portfolioId, PortfolioUpdateDTO dto) {
        return portfolioService.update(portfolioId, dto);
    }

    public void delete(Long portfolioId) {
        portfolioService.delete(portfolioId);
    }
}
