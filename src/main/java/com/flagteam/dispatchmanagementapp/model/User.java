package com.flagteam.dispatchmanagementapp.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long versionId=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Date create;
    @OneToMany(mappedBy = "user")
    private Set<DeliveryInfo> deliveryInfos;

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreate() {
        return create;
    }

    public User setCreate(Date create) {
        this.create = create;
        return this;
    }
}
