package com.dudzinski.portfolio.domain.currency;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rate", precision = 15, scale = 4)
    private BigDecimal rate;

    @Column(name = "code")
    private String code;

    public CurrencyEntity(String name, BigDecimal rate, String code) {
        this.name = name;
        this.rate = rate;
        this.code = code.toUpperCase();
    }

}
