package com.flagteam.dispatchmanagementapp.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "location")
public class Location implements Serializable {
    private static final long versionId=1L;
    @Id
    private UUID id;
    private String type;
    private Double latitude;
    private Double longitude;
    private Date create;
    private Date update;

    @OneToMany
    @JoinColumn(name = "delivery_id")
    private Set<DeliveryInfo> deliveryInfos;

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

    public Date getCreate() {
        return create;
    }

    public Location setCreate(Date create) {
        this.create = create;
        return this;
    }

    public Date getUpdate() {
        return update;
    }

    public Location setUpdate(Date update) {
        this.update = update;
        return this;
    }
}
