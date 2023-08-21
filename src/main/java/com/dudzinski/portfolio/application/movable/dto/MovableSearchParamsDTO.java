package com.dudzinski.portfolio.application.movable.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovableSearchParamsDTO {

    private String name;

    private String brand;

    private Pageable pageable;
}