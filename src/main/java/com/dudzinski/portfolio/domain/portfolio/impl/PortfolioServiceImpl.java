package com.dudzinski.portfolio.domain.portfolio.impl;

import com.dudzinski.portfolio.application.portfolio.dto.PortfolioPersistDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchParamsDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchResultDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioUpdateDTO;
import com.dudzinski.portfolio.application.portfolio.mapper.PortfolioMapper;
import com.dudzinski.portfolio.domain.portfolio.PortfolioEntity;
import com.dudzinski.portfolio.domain.portfolio.PortfolioService;
import com.dudzinski.portfolio.domain.portfolio.PortfolioType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;

    @Override
    public PortfolioEntity persist(PortfolioPersistDTO dto) {
        return portfolioRepository.save(portfolioMapper.toEntity(dto));
    }

    @Override
    public Page<PortfolioSearchResultDTO> search(PortfolioSearchParamsDTO searchParamsDTO) {

        return portfolioRepository.findAll(
                        portfolioRepository.buildSpecification(searchParamsDTO),
                        searchParamsDTO.getPageable()
                )
                .map(portfolioMapper::toPortfolioSearchResultDTO);
    }

    @Override
    public PortfolioSearchResultDTO getById(Long portfolioId) {
        return portfolioMapper.toPortfolioSearchResultDTO(portfolioRepository.getById(portfolioId));
    }

    @Override
    public PortfolioSearchResultDTO update(Long portfolioId, PortfolioUpdateDTO dto) {
        PortfolioEntity portfolio = portfolioRepository.getById(portfolioId);
        portfolio.setName(dto.getName());
        portfolio.setQuantity(dto.getQuantity());
        portfolio.setPrice(dto.getPrice());
        portfolio.setTotalPrice(dto.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
        portfolio.setType(PortfolioType.from(dto.getType()));

        return portfolioMapper.toPortfolioSearchResultDTO(portfolioRepository.save(portfolio));
    }

    @Override
    public void delete(Long portfolioId) {
        portfolioRepository.delete(portfolioId);
    }
}
