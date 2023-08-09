package com.dudzinski.portfolio.domain.car;

import com.dudzinski.portfolio.domain.client.ClientEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String bodyType;
    private int productionYear;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private ClientEntity owner;

    public CarEntity(String brand,
                     String model,
                     String bodyType,
                     int productionYear,
                     ClientEntity owner) {
        this.brand = brand;
        this.model = model;
        this.bodyType = bodyType;
        this.productionYear = productionYear;
        this.owner = owner;
    }

}
