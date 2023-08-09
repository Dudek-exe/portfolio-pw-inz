package com.dudzinski.portfolio.rest.car;

import com.dudzinski.portfolio.domain.client.ClientEntity;

public class NewCarRequest {

    private String brand;
    private String model;
    private String bodyType;
    private int productionYear;
    private ClientEntity clientEntity;


    public NewCarRequest(String brand, String model, String bodyType, int productionYear, ClientEntity clientEntity) {
        this.brand = brand;
        this.model = model;
        this.bodyType = bodyType;
        this.productionYear = productionYear;
        this.clientEntity = clientEntity;
    }

    public NewCarRequest() {
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
}
