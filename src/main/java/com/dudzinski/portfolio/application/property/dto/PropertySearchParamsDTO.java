package com.dudzinski.portfolio.application.property.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertySearchParamsDTO {

    private String name;

    private Pageable pageable;
}