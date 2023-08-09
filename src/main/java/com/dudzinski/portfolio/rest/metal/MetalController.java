package com.dudzinski.portfolio.rest.metal;

import com.dudzinski.portfolio.domain.metal.MetalEntity;
import com.dudzinski.portfolio.domain.metal.MetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "metal")
public class MetalController {

    final MetalService metalService;

    @Autowired
    public MetalController(MetalService metalServiceImpl) {
        this.metalService = metalServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    List<NewMetalResponse> getAll() {
        return metalService.getAll();
    }

    @PostMapping
    MetalEntity save(@RequestBody NewMetalRequest newMetalRequest) {
        return metalService.createNewMetal(newMetalRequest.getName(), newMetalRequest.getPrice());
    }


}
