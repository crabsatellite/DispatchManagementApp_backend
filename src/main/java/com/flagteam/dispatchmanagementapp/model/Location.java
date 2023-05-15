package com.flagteam.dispatchmanagementapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "location")
public class Location implements Serializable {
    private static final long versionId = 1L;
    @Id
    private UUID id;
    private String pointType;
    private Double latitude;
    private Double longitude;
    private Date createTime;
    private Date updateTime;

    @OneToMany
    @JoinColumn(name = "deliveries")
    private Set<DeliveryInfo> deliveryInfos;

    public UUID getId() {
        return id;
    }

    public Location setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return pointType;
    }

    public Location setType(String type) {
        this.pointType = type;
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
        return createTime;
    }

    public Location setCreate(Date create) {
        this.createTime = create;
        return this;
    }

    public Date getUpdate() {
        return updateTime;
    }

    public Location setUpdate(Date update) {
        this.updateTime = update;
        return this;
    }
}
