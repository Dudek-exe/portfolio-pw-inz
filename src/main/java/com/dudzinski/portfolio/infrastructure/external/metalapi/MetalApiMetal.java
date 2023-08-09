package com.dudzinski.portfolio.infrastructure.external.metalapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetalApiMetal implements Serializable {

    private Boolean success;

    private Date timestamp;

    private String base;

    private Map<String, Double> rates;
}

