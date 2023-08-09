package com.dudzinski.portfolio.rest.car;

import com.dudzinski.portfolio.domain.car.CarEntity;
import com.dudzinski.portfolio.domain.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CarController {

    final CarService carService;

    @Autowired
    public CarController(CarService carServiceImpl) {
        this.carService = carServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER')")
    List<CarEntity> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{brand}")
    List<CarEntity> getAllByBrand(@PathVariable String brand) {
        return carService.getAllByBrand(brand);
    }

    @PostMapping
    CarEntity save(@RequestBody NewCarRequest newCarRequest) {
        return carService.createNewCar(newCarRequest.getBrand(), newCarRequest.getModel(), newCarRequest.getBodyType(), newCarRequest.getProductionYear(), newCarRequest.getClient());
    }

}
