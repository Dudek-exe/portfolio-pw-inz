package com.dudzinski.portfolio.rest.movable;

import com.dudzinski.portfolio.application.movable.MovableFacade;
import com.dudzinski.portfolio.application.movable.dto.MovablePersistDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchParamsDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchResultDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableUpdateDTO;
import com.dudzinski.portfolio.domain.movable.MovableEntity;
import com.dudzinski.portfolio.infrastructure.util.HTTPVoidWrapperDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = MovableControllerConstants.RESOURCE_CURRENCY)
public class MovableController {

    private final MovableFacade movableFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<MovableSearchResultDTO> search(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String brand,
                                        @RequestParam(name = "_start") int start,
                                        @RequestParam(name = "_end") int end,
                                        @RequestParam(name = "_order") String order,
                                        @RequestParam(name = "_sort") String sort) {
        return movableFacade.search(wrapSearchParams(
                        name,
                        brand,
                        start,
                        end,
                        order,
                        sort
                )
        );
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping("/{movableId}")
    MovableSearchResultDTO get(@PathVariable Long movableId) {
        return movableFacade.getById(movableId);

    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PutMapping("/{movableId}")
    MovableSearchResultDTO update(@PathVariable Long movableId,
                                  @RequestBody MovableUpdateDTO dto) {
        return movableFacade.update(movableId, dto);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @DeleteMapping("/{movableId}")
    ResponseEntity<HTTPVoidWrapperDTO> delete(@PathVariable Long movableId) {
        movableFacade.delete(movableId);
        return ResponseEntity.ok(new HTTPVoidWrapperDTO("Success"));
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    ResponseEntity<MovableEntity> save(@RequestBody MovablePersistDTO dto) {
        MovableEntity movable = movableFacade.persist(dto);
        return ResponseEntity.ok(movable);
    }

    private MovableSearchParamsDTO wrapSearchParams(
            String name,
            String brand,
            int start,
            int end,
            String order,
            String sort) {
        return new MovableSearchParamsDTO(
                name,
                brand,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
