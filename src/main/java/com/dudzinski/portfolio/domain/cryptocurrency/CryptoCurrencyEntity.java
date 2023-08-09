package com.dudzinski.portfolio.domain.cryptocurrency;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_crypto_currency")
public class CryptoCurrencyEntity {

    @Id
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rate", precision = 15, scale = 8)
    private BigDecimal rate;

    @Column(name = "code")
    private String code;

    public CryptoCurrencyEntity(String name, BigDecimal rate, String code) {
        this.name = name;
        this.rate = rate;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoCurrencyEntity that = (CryptoCurrencyEntity) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
