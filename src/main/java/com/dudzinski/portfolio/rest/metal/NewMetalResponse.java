package com.dudzinski.portfolio.rest.metal;

import com.dudzinski.portfolio.domain.metal.MetalEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class NewMetalResponse {

    private final Long id;
    private final String name;
    private final BigDecimal price;

    public static NewMetalResponse from(MetalEntity metalEntity) {
        return new NewMetalResponse(metalEntity.getId(), metalEntity.getName(), metalEntity.getPrice());
    }

}
