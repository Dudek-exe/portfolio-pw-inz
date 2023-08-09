package com.dudzinski.portfolio.domain.car.impl;

import com.dudzinski.portfolio.domain.car.CarEntity;
import com.dudzinski.portfolio.domain.car.CarService;
import com.dudzinski.portfolio.domain.client.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarEntity createNewCar(String brand, String model, String bodyType, int productionYear, ClientEntity clientEntity) {
        CarEntity carEntity = new CarEntity(brand, model, bodyType, productionYear, clientEntity);
        return carRepository.save(carEntity);
    }

    @Override
    public List<CarEntity> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<CarEntity> getAllByBrand(String brand) {
        return carRepository.findAllByBrand(brand);
    }

}
