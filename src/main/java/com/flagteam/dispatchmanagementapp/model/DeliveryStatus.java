package com.flagteam.dispatchmanagementapp.model;

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
    private UUID id;
    private String status;

    public UUID getId() {
        return id;
    }

    public DeliveryStatus setId(UUID id) {
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
}
