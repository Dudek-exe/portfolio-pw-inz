package com.dudzinski.portfolio.rest.metal;

public class NewMetalRequest {

    private String name;
    private Double price;

    public NewMetalRequest(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public NewMetalRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
