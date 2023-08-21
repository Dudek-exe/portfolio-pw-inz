package com.dudzinski.portfolio.domain.cryptocurrency;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "pc_15m")
    private Double pc15m;

    @Column(name = "pc_30m")
    private Double pc30m;

    @Column(name = "pc_1h")
    private Double pc1h;

    @Column(name = "pc_6h")
    private Double pc6h;

    @Column(name = "pc_12h")
    private Double pc12h;

    @Column(name = "pc_1d")
    private Double pc1d;

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
