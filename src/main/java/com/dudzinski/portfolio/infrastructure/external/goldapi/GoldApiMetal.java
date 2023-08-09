package com.dudzinski.portfolio.infrastructure.external.goldapi;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class GoldApiMetal implements Serializable {

    private Boolean success;
    private Date timestamp;
    private String date;
    private String base;
    private Map<String, Double> rates;

    public GoldApiMetal(Boolean success, Date timestamp, String date, String base, Map<String, Double> rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.date = date;
        this.base = base;
        this.rates = rates;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }


}

