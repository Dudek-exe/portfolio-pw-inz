package com.dudzinski.portfolio.domain.metal;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_metal")
public class MetalEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "price", precision = 15, scale = 4)
    private BigDecimal price;

    public MetalEntity(String name, String code, LocalDateTime date, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.date = date;
        this.price = price;
    }
}
