package com.dudzinski.portfolio.domain.metal;

import com.dudzinski.portfolio.rest.metal.NewMetalResponse;

import java.util.List;

public interface MetalService {

    void updateMetal(String name, Double price);

    List<NewMetalResponse> getAll();

}
