package com.dudzinski.portfolio.application.car.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarSearchParamsDTO {

    private String name;

    private String brand;

    private Pageable pageable;
}