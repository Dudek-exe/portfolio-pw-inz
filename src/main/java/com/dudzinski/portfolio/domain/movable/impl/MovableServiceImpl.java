package com.dudzinski.portfolio.domain.movable.impl;

import com.dudzinski.portfolio.application.movable.dto.MovablePersistDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchParamsDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableSearchResultDTO;
import com.dudzinski.portfolio.application.movable.dto.MovableUpdateDTO;
import com.dudzinski.portfolio.application.movable.mapper.MovableMapper;
import com.dudzinski.portfolio.domain.movable.MovableEntity;
import com.dudzinski.portfolio.domain.movable.MovableService;
import com.dudzinski.portfolio.domain.movable.MovableType;
import com.dudzinski.portfolio.domain.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MovableServiceImpl implements MovableService {

    private final MovableRepository movableRepository;
    private final MovableMapper movableMapper;

    @Override
    public MovableEntity persist(MovablePersistDTO dto) {

        MovableEntity movable = movableMapper.toEntity(dto);
        movable.setUsernameOwner(SecurityUtils.getLoggedUserLogin());
        return movableRepository.save(movable);
    }

    @Override
    public Page<MovableSearchResultDTO> search(MovableSearchParamsDTO searchParamsDTO) {

        return movableRepository.findAll(
                        movableRepository.buildSpecification(searchParamsDTO),
                        searchParamsDTO.getPageable()
                )
                .map(movableMapper::toMovableSearchResultDTO);
    }

    @Override
    public MovableSearchResultDTO getById(Long movableId) {
        return movableMapper.toMovableSearchResultDTO(movableRepository.getById(movableId));
    }

    @Override
    public MovableSearchResultDTO update(Long movableId, MovableUpdateDTO dto) {
        MovableEntity movable = movableRepository.getById(movableId);
        movable.setName(dto.getName());
        movable.setBrand(dto.getBrand());
        movable.setModel(dto.getModel());
        movable.setProductionYear(dto.getProductionYear());
        movable.setPurchasePrice(dto.getPurchasePrice());
        movable.setPurchaseDate(dto.getPurchaseDate());
        movable.setEstimatedValue(dto.getEstimatedValue());
        movable.setType(MovableType.from(dto.getMovableType()));

        return movableMapper.toMovableSearchResultDTO(movableRepository.save(movable));
    }

    @Override
    public void delete(Long movableId) {
        movableRepository.delete(movableId);
    }

}
