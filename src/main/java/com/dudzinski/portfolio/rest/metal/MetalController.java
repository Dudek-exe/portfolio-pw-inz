package com.dudzinski.portfolio.rest.metal;

import com.dudzinski.portfolio.domain.metal.MetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "metal")
public class MetalController {

    final MetalService metalService;

    @Autowired
    public MetalController(MetalService metalServiceImpl) {
        this.metalService = metalServiceImpl;
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    List<NewMetalResponse> getAll() {
        return metalService.getAll();
    }

}
