package com.dudzinski.portfolio.application.movable;

import com.dudzinski.portfolio.application.movable.dto.MovablePersistDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchParamsDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchResultDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableUpdateDTO;
import com.dudzinski.portfolio.domain.movable.MovableEntity;
import com.dudzinski.portfolio.domain.movable.MovableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovableFacade {

    private final MovableService movableServiceImpl;

    public MovableEntity persist(MovablePersistDTO dto) {
        return movableServiceImpl.persist(dto);
    }

    public Page<MovableSearchResultDTO> search(MovableSearchParamsDTO dto) {
        return movableServiceImpl.search(dto);
    }

    public MovableSearchResultDTO getById(Long movableId) {
        return movableServiceImpl.getById(movableId);
    }

    public MovableSearchResultDTO update(Long movableId, MovableUpdateDTO dto) {
        return movableServiceImpl.update(movableId, dto);
    }

    public void delete(Long movableId) {
        movableServiceImpl.delete(movableId);
    }
}
