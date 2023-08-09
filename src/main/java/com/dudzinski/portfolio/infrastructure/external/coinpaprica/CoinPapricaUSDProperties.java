package com.dudzinski.portfolio.infrastructure.external.coinpaprica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CoinPapricaUSDProperties implements Serializable {

    private Double price;
    private Double volume_24h;
    private Double volume_24h_change_24h;
    private Double market_cap;
    private Double market_cap_change_24h;
    private Double percent_change_15m;
    private Double percent_change_30m;
    private Double percent_change_1h;
    private Double percent_change_6h;
    private Double percent_change_12h;
    private Double percent_change_24h;
    private Double percent_change_7d;
    private Double percent_change_30d;
    private Double percent_change_1y;
    private Double ath_price;
    private String ath_date;
    private Double percent_from_price_ath;

    public CoinPapricaUSDProperties(Double price, Double volume_24h, Double volume_24h_change_24h, Double market_cap, Double market_cap_change_24h, Double percent_change_15m, Double percent_change_30m, Double percent_change_1h, Double percent_change_6h, Double percent_change_12h, Double percent_change_24h, Double percent_change_7d, Double percent_change_30d, Double percent_change_1y, Double ath_price, String ath_date, Double percent_from_price_ath) {
        this.price = price;
        this.volume_24h = volume_24h;
        this.volume_24h_change_24h = volume_24h_change_24h;
        this.market_cap = market_cap;
        this.market_cap_change_24h = market_cap_change_24h;
        this.percent_change_15m = percent_change_15m;
        this.percent_change_30m = percent_change_30m;
        this.percent_change_1h = percent_change_1h;
        this.percent_change_6h = percent_change_6h;
        this.percent_change_12h = percent_change_12h;
        this.percent_change_24h = percent_change_24h;
        this.percent_change_7d = percent_change_7d;
        this.percent_change_30d = percent_change_30d;
        this.percent_change_1y = percent_change_1y;
        this.ath_price = ath_price;
        this.ath_date = ath_date;
        this.percent_from_price_ath = percent_from_price_ath;
    }

    @Override
    public String toString() {
        return "CoinPapricaUSDProperties{" +
                "price=" + price +
                ", volume_24h=" + volume_24h +
                ", volume_24h_change_24h=" + volume_24h_change_24h +
                ", market_cap=" + market_cap +
                ", market_cap_change_24h=" + market_cap_change_24h +
                ", percent_change_15m=" + percent_change_15m +
                ", percent_change_30m=" + percent_change_30m +
                ", percent_change_1h=" + percent_change_1h +
                ", percent_change_6h=" + percent_change_6h +
                ", percent_change_12h=" + percent_change_12h +
                ", percent_change_24h=" + percent_change_24h +
                ", percent_change_7d=" + percent_change_7d +
                ", percent_change_30d=" + percent_change_30d +
                ", percent_change_1y=" + percent_change_1y +
                ", ath_price=" + ath_price +
                ", ath_date='" + ath_date + '\'' +
                ", percent_from_price_ath=" + percent_from_price_ath +
                '}';
    }
}
