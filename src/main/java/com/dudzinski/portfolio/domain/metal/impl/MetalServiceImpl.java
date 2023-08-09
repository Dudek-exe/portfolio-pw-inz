package com.dudzinski.portfolio.domain.metal.impl;

import com.dudzinski.portfolio.domain.metal.MetalEntity;
import com.dudzinski.portfolio.domain.metal.MetalService;
import com.dudzinski.portfolio.rest.metal.NewMetalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class MetalServiceImpl implements MetalService {

    private final MetalRepository metalRepository;

    @Override
    public MetalEntity createNewMetal(String name, Double price) {
        MetalEntity newMetalEntity = new MetalEntity(name, BigDecimal.valueOf(price));
        return metalRepository.save(newMetalEntity);
    }

    @Override
    public void updateMetal(String name, Double price) {
        Optional<MetalEntity> optionalMetal = metalRepository.findByNameIgnoreCase(name);

        if (optionalMetal.isPresent()) {
            MetalEntity metalEntity = optionalMetal.get();
            metalEntity.setPrice(BigDecimal.valueOf(price));
            metalRepository.save(metalEntity);
        } else {
            MetalEntity newMetalEntity = new MetalEntity(name, BigDecimal.valueOf(price));
            metalRepository.save(newMetalEntity);
        }
    }

    @Override
    public List<NewMetalResponse> getAll() {
        return metalRepository.findAll().stream()
                .map(NewMetalResponse::from)
                .toList();
    }
}
