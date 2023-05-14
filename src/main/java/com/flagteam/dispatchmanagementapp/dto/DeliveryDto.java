package com.flagteam.dispatchmanagementapp.dto;

public class DeliveryDto {
    // all fields from DeliveryItem
    private Double courierLastPositionLat;
    private Double courierLastPositionLng;

    public Double getCourierLastPositionLat() {
        return courierLastPositionLat;
    }

    public void setCourierLastPositionLat(Double courierLastPositionLat) {
        this.courierLastPositionLat = courierLastPositionLat;
    }

    public Double getCourierLastPositionLng() {
        return courierLastPositionLng;
    }

    public void setCourierLastPositionLng(Double courierLastPositionLng) {
        this.courierLastPositionLng = courierLastPositionLng;
    }
}
