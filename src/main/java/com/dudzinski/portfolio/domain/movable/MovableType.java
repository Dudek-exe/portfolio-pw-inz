package com.dudzinski.portfolio.domain.movable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum MovableType {

    CAR("Samochód"),
    BOAT("Łodź"),
    YACHT("Jacht"),
    MOTO("Motocykl"),
    ELECTRONICS("Elektronika"),
    OTHERS("Inne");

    private final String description;

    public static MovableType from(String type) {
        return MovableType.valueOf(type.toUpperCase());
    }

}