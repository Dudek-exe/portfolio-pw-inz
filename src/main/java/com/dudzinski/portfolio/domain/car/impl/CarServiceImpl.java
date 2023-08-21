package com.dudzinski.portfolio.domain.car.impl;

import com.dudzinski.portfolio.application.car.dto.CarPersistDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchParamsDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchResultDTO;
import com.dudzinski.portfolio.application.car.dto.CarUpdateDTO;
import com.dudzinski.portfolio.application.car.mapper.CarMapper;
import com.dudzinski.portfolio.domain.car.CarEntity;
import com.dudzinski.portfolio.domain.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarEntity persistCar(CarPersistDTO dto) {
        return carRepository.save(carMapper.toEntity(dto));
    }

    @Override
    public Page<CarSearchResultDTO> search(CarSearchParamsDTO searchParamsDTO) {

        return carRepository.findAll(
                        carRepository.buildSpecification(searchParamsDTO),
                        searchParamsDTO.getPageable()
                )
                .map(carMapper::toCarSearchResultDTO);
    }

    @Override
    public CarSearchResultDTO getById(Long carId) {
        return carMapper.toCarSearchResultDTO(carRepository.getById(carId));
    }

    @Override
    public CarSearchResultDTO update(Long carId, CarUpdateDTO dto) {
        CarEntity car = carRepository.getById(carId);
        car.setName(dto.getName());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setProductionYear(dto.getProductionYear());
        car.setPurchasePrice(dto.getPurchasePrice());
        car.setEstimatedValue(dto.getEstimatedValue());

        return carMapper.toCarSearchResultDTO(carRepository.save(car));
    }

    @Override
    public void delete(Long carId) {
        carRepository.delete(carId);
    }

}
