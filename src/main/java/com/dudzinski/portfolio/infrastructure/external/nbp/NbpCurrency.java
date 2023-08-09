package com.dudzinski.portfolio.infrastructure.external.nbp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NbpCurrency implements Serializable {

    private String currency;

    private String code;

    private Double mid;

    private LocalDate date;

    @Override
    public String toString() {
        return "NbpCurrency{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", mid=" + String.format("%.7f", mid) +
                '}';
    }
}
