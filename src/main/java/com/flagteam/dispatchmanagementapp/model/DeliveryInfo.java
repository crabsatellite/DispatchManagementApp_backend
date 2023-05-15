package com.flagteam.dispatchmanagementapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "delivery_info")
public class DeliveryInfo implements Serializable {
    private static final long versionId = 1L;
    @Id
    private UUID id;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Column(name = "warehouse_id")
    private int warehouseId;
    @Column(name = "is_robot")
    private boolean isRobot;
    private String sender;
    private String receiver;
    @Column(name = "sender_address")
    private String senderAddress;
    @Column(name = "receiver_address")
    private String receiverAddress;
    @Column(name = "pick_up_speed")
    private String pickUpSpeed;
    @Column(name = "delivery_speed")
    private String deliverySpeed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "status")
    private DeliveryStatus deliveryStatus;

    public UUID getId() {
        return id;
    }

    public DeliveryInfo setId(UUID id) {
        this.id = id;
        return this;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public DeliveryInfo setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public DeliveryInfo setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
        return this;
    }

    public boolean isRobot() {
        return isRobot;
    }

    public DeliveryInfo setRobot(boolean robot) {
        isRobot = robot;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public DeliveryInfo setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public DeliveryInfo setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public DeliveryInfo setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public DeliveryInfo setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public String getPickUpSpeed() {
        return pickUpSpeed;
    }

    public DeliveryInfo setPickUpSpeed(String pickUpSpeed) {
        this.pickUpSpeed = pickUpSpeed;
        return this;
    }

    public String getDeliverySpeed() {
        return deliverySpeed;
    }

    public DeliveryInfo setDeliverySpeed(String deliverySpeed) {
        this.deliverySpeed = deliverySpeed;
        return this;
    }
}
