package com.dudzinski.portfolio.application.car;

import com.dudzinski.portfolio.application.car.dto.CarPersistDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchParamsDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchResultDTO;
import com.dudzinski.portfolio.application.car.dto.CarUpdateDTO;
import com.dudzinski.portfolio.domain.car.CarEntity;
import com.dudzinski.portfolio.domain.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarFacade {

    private final CarService carServiceImpl;

    public CarEntity persistCar(CarPersistDTO dto) {
        return carServiceImpl.persistCar(dto);
    }

    public Page<CarSearchResultDTO> search(CarSearchParamsDTO dto) {
        return carServiceImpl.search(dto);
    }

    public CarSearchResultDTO getById(Long carId) {
        return carServiceImpl.getById(carId);
    }

    public CarSearchResultDTO update(Long carId, CarUpdateDTO dto) {
        return carServiceImpl.update(carId, dto);
    }

    public void delete(Long carId) {
        carServiceImpl.delete(carId);
    }
}
