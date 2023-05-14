package com.flagteam.dispatchmanagementapp.dto;

import java.util.Set;
import java.util.UUID;

public class DeliveryDto {
    private UUID id;
    private int quantity;
    private Double courierLastPositionLat;
    private Double courierLastPositionLng;
    private UUID deliveryInfoId;
    private Set<UUID> itemIds;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // getters and setters for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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

    // getters and setters for deliveryInfoId
    public UUID getDeliveryInfoId() {
        return deliveryInfoId;
    }

    public void setDeliveryInfoId(UUID deliveryInfoId) {
        this.deliveryInfoId = deliveryInfoId;
    }

    // getters and setters for itemIds
    public Set<UUID> getItemIds() {
        return itemIds;
    }

    public void setItemIds(Set<UUID> itemIds) {
        this.itemIds = itemIds;
    }
}
