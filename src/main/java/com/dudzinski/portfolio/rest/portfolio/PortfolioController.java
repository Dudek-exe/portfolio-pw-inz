package com.dudzinski.portfolio.rest.portfolio;

import com.dudzinski.portfolio.application.portfolio.PortfolioFacade;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioPersistDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchParamsDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioSearchResultDTO;
import com.dudzinski.portfolio.application.portfolio.dto.PortfolioUpdateDTO;
import com.dudzinski.portfolio.infrastructure.util.HTTPVoidWrapperDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = PortfolioControllerConstants.RESOURCE_CURRENCY)
public class PortfolioController {

    private final PortfolioFacade portfolioFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<PortfolioSearchResultDTO> search(@RequestParam(required = false) String name,
                                          @RequestParam(name = "_start") int start,
                                          @RequestParam(name = "_end") int end,
                                          @RequestParam(name = "_order") String order,
                                          @RequestParam(name = "_sort") String sort) {
        return portfolioFacade.search(wrapSearchParams(
                        name,
                        start,
                        end,
                        order,
                        sort
                )
        );
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping("/{portfolioId}")
    PortfolioSearchResultDTO get(@PathVariable Long portfolioId) {
        return portfolioFacade.getById(portfolioId);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PutMapping("/{portfolioId}")
    PortfolioSearchResultDTO update(@PathVariable Long portfolioId,
                                    @RequestBody PortfolioUpdateDTO dto) {
        return portfolioFacade.update(portfolioId, dto);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @DeleteMapping("/{portfolioId}")
    ResponseEntity<HTTPVoidWrapperDTO> delete(@PathVariable Long portfolioId) {
        portfolioFacade.delete(portfolioId);
        return ResponseEntity.ok(new HTTPVoidWrapperDTO("Success"));
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    ResponseEntity<HTTPVoidWrapperDTO> save(@RequestBody PortfolioPersistDTO dto) {
        portfolioFacade.persist(dto);
        return ResponseEntity.ok(new HTTPVoidWrapperDTO("Success"));
    }

    private PortfolioSearchParamsDTO wrapSearchParams(
            String name,
            int start,
            int end,
            String order,
            String sort) {
        return new PortfolioSearchParamsDTO(
                name,
                null,
                null,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
