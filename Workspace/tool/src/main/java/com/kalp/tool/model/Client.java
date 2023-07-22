package com.kalp.tool.model;

import javax.persistence.*;

@Entity
@Table(name = "clients", schema = "admin")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_info")
    private String contactInfo;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    // Getters and Setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    // Constructors

    // Other methods
}
