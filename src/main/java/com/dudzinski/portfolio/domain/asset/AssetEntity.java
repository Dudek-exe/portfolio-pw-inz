package com.dudzinski.portfolio.domain.asset;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_asset")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //FIXME CHANGE TO BIGDECIMAL
    @Column(name = "price")
    private String price;

    public AssetEntity(String name, String price) {
        this.name = name;
        this.price = price;
    }

}
