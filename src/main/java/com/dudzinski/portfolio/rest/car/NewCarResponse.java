package com.dudzinski.portfolio.rest.car;

import com.dudzinski.portfolio.domain.client.ClientEntity;

public class NewCarResponse {

    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private int productionYear;
    private ClientEntity clientEntity;


    public NewCarResponse(Long id, String brand, String model, String bodyType, int productionYear, ClientEntity clientEntity) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.bodyType = bodyType;
        this.productionYear = productionYear;
        this.clientEntity = clientEntity;
    }

    public NewCarResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public ClientEntity getClient() {
        return clientEntity;
    }

    // public static NewCarResponse from(Car car) {
    //   //  return new NewCarResponse(car.getId(), car.getBrand(), car.getModel(), car.getBodyType(), car.getProductionYear(), );
    // }
}
