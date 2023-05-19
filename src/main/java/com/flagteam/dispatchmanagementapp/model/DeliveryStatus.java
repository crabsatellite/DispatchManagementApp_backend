package com.flagteam.dispatchmanagementapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "delivery_status_table")
public class DeliveryStatus implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="status_id")
    private long id;
    private String status;

    @JsonIgnore
    @OneToOne(mappedBy = "deliveryStatus", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_info_id")
    private DeliveryInfo deliveryInfo;

    public long getId() {
        return id;
    }

    public DeliveryStatus setId(long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public DeliveryStatus setStatus(String status) {
        this.status = status;
        return this;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public DeliveryStatus setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        return this;
    }
}
