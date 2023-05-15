package com.flagteam.dispatchmanagementapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "delivery_status")
public class DeliveryStatus implements Serializable {
    private static final long versionId = 1L;
    @Id
    private UUID id;
    private Status status;

    public UUID getId() {
        return id;
    }

    public DeliveryStatus setId(UUID id) {
        this.id = id;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public DeliveryStatus setStatus(Status status) {
        this.status = status;
        return this;
    }
}
