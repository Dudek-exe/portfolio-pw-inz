package com.dudzinski.portfolio.infrastructure.external.coinpaprica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoinPapricaCryptoCurrency implements Serializable {

    private String id;
    private String name;
    private String symbol;
    private Double rank;
    private Double circulating_supply;
    private Double total_supply;
    private Double max_supply;
    private Double beta_value;
    private String first_data_at;
    private String last_updated;
    private Double price;
    private Map<String, CoinPapricaUSDProperties> quotes;

    @Override
    public String toString() {
        return "CoinPapricaCryptoCurrency{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank='" + rank + '\'' +
                ", circulating_supply='" + circulating_supply + '\'' +
                ", total_supply='" + total_supply + '\'' +
                ", max_supply='" + max_supply + '\'' +
                ", beta_value='" + beta_value + '\'' +
                ", first_data_at='" + first_data_at + '\'' +
                ", last_updated='" + last_updated + '\'' +
                ", price=" + price +
                ", quotes=" + quotes +
                '}';
    }

}