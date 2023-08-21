package com.dudzinski.portfolio.rest.car;

import com.dudzinski.portfolio.application.car.CarFacade;
import com.dudzinski.portfolio.application.car.dto.CarPersistDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchParamsDTO;
import com.dudzinski.portfolio.application.car.dto.CarSearchResultDTO;
import com.dudzinski.portfolio.application.car.dto.CarUpdateDTO;
import com.dudzinski.portfolio.domain.car.CarEntity;
import com.dudzinski.portfolio.infrastructure.util.HTTPVoidWrapperDTO;
import com.dudzinski.portfolio.infrastructure.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = CarControllerConstants.RESOURCE_CURRENCY)
public class CarController {

    private final CarFacade carFacade;

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping
    Page<CarSearchResultDTO> search(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String brand,
                                    @RequestParam(name = "_start") int start,
                                    @RequestParam(name = "_end") int end,
                                    @RequestParam(name = "_order") String order,
                                    @RequestParam(name = "_sort") String sort) {
        return carFacade.search(wrapSearchParams(
                        name,
                        brand,
                        start,
                        end,
                        order,
                        sort
                )
        );
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @GetMapping("/{carId}")
    CarSearchResultDTO get(@PathVariable Long carId) {
        return carFacade.getById(carId);

    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PutMapping("/{carId}")
    CarSearchResultDTO update(@PathVariable Long carId,
                              @RequestBody CarUpdateDTO dto) {
        return carFacade.update(carId, dto);
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @DeleteMapping("/{carId}")
    ResponseEntity<HTTPVoidWrapperDTO> delete(@PathVariable Long carId) {
        carFacade.delete(carId);
        return ResponseEntity.ok(new HTTPVoidWrapperDTO("Success"));
    }

    @PreAuthorize("hasAnyRole(T(com.dudzinski.portfolio.domain.client.RoleType).BASIC_USER.name(),"
            + "T(com.dudzinski.portfolio.domain.client.RoleType).ADMIN.name())")
    @PostMapping
    ResponseEntity<CarEntity> save(@RequestBody CarPersistDTO dto) {
        CarEntity car = carFacade.persistCar(dto);
        return ResponseEntity.ok(car);
    }

    private CarSearchParamsDTO wrapSearchParams(
            String name,
            String brand,
            int start,
            int end,
            String order,
            String sort) {
        return new CarSearchParamsDTO(
                name,
                brand,
                PageableUtils.preparePageable(start, end, sort, order)
        );
    }
}
