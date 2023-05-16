package com.flagteam.dispatchmanagementapp.model;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "location_table")
public class Location implements Serializable {
    private static final long versionId=1L;
    @Id
    private UUID id;
    private String type;
    private Double latitude;
    private Double longitude;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryInfo deliveryInfo;

    public UUID getId() {
        return id;
    }

    public Location setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Location setType(String type) {
        this.type = type;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Location setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Location setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocalDate getCreate() {
        return createdDate;
    }

    public Location setCreate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDate getUpdate() {
        return updatedDate;
    }

    public Location setUpdate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }
}
