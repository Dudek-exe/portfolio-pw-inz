package com.dudzinski.portfolio.domain.car;

import com.dudzinski.portfolio.domain.client.ClientEntity;

import java.util.List;

public interface CarService {

    CarEntity createNewCar(String brand, String model, String bodyType, int productionYear, ClientEntity clientEntity);

    List<CarEntity> getAll();

    List<CarEntity> getAllByBrand(String brand);
}
