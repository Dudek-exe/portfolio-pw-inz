package com.dudzinski.portfolio.domain.currency;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_currency")
public class CurrencyEntity {

    @Id
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rate", precision = 15, scale = 4)
    private BigDecimal rate;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private LocalDate date;

    public CurrencyEntity(String name, BigDecimal rate, String code, LocalDate date) {
        this.name = name;
        this.rate = rate;
        this.code = code.toUpperCase();
        this.date = date;
    }

}
