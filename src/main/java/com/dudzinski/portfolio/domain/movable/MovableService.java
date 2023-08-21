package com.dudzinski.portfolio.domain.movable;

import com.dudzinski.portfolio.application.movable.dto.MovablePersistDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchParamsDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchResultDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableUpdateDTO;
import org.springframework.data.domain.Page;

public interface MovableService {

    MovableEntity persist(MovablePersistDTO dto);

    Page<MovableSearchResultDTO> search(MovableSearchParamsDTO dto);

    MovableSearchResultDTO getById(Long movableId);

    MovableSearchResultDTO update(Long movableId, MovableUpdateDTO dto);

    void delete(Long movableId);
}
