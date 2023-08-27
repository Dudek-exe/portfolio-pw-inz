package com.dudzinski.portfolio.domain.metal.impl;

import com.dudzinski.portfolio.domain.metal.MetalEntity;
import com.dudzinski.portfolio.domain.metal.MetalService;
import com.dudzinski.portfolio.rest.metal.NewMetalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class MetalServiceImpl implements MetalService {

    private final MetalRepository metalRepository;

    @Override
    public void updateMetal(String code, Double price) {
        MetalEntity newMetalEntity = new MetalEntity(getName(code), code, LocalDateTime.now(), BigDecimal.valueOf(price));
        metalRepository.save(newMetalEntity);
    }

    @Override
    public List<NewMetalResponse> getAll() {
        return metalRepository.findAll().stream()
                .map(NewMetalResponse::from)
                .toList();
    }

    private String getName(String code) {
        return switch (code) {
            case "XAG" -> "Srebro";
            case "XAU" -> "Złoto";
            case "XPD" -> "Pallad";
            case "XPT" -> "Platyna";
            default -> null;
        };
    }
}
