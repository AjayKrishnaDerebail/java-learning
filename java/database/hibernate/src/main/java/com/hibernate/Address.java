package com.hibernate;

import javax.persistence.*;

import java.util.Date;
@Entity
@Table(name = "Student_Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private int addressId;
    @Column(length = 100, nullable = false)
    private String street;
    @Column(length = 40, nullable = false)
    private String city;
    private boolean isOpen;
    @Transient
    private double x;
    @Temporal(TemporalType.DATE)
    private Date addedDate;
    //@Lob

    public Address () {
    }

    public Address (int addressId, String street, String city, boolean isOpen, double x, Date addedDate) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.isOpen = isOpen;
        this.x = x;
        this.addedDate = addedDate;
    }


    public int getAddressId () {
        return addressId;
    }

    public void setAddressId (int addressId) {
        this.addressId = addressId;
    }

    public String getStreet () {
        return street;
    }

    public void setStreet (String street) {
        this.street = street;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public boolean isOpen () {
        return isOpen;
    }

    public void setOpen (boolean open) {
        isOpen = open;
    }

    public double getX () {
        return x;
    }

    public void setX (double x) {
        this.x = x;
    }

    public Date getAddedDate () {
        return addedDate;
    }

    public void setAddedDate (Date addedDate) {
        this.addedDate = addedDate;
    }

}
