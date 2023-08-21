package com.dudzinski.portfolio.domain.car;

import com.dudzinski.portfolio.application.car.dto.CarPersistDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchParamsDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchResultDTO;
import com.dudzinski.portfolio.application.car.dto.CarUpdateDTO;
import org.springframework.data.domain.Page;

public interface CarService {

    CarEntity persistCar(CarPersistDTO dto);

    Page<CarSearchResultDTO> search(CarSearchParamsDTO dto);

    CarSearchResultDTO getById(Long carId);

    CarSearchResultDTO update(Long carId, CarUpdateDTO dto);

    void delete(Long carId);
}
