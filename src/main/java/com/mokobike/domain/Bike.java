package com.mokobike.domain;

import java.util.Date;
import java.util.Objects;

public class Bike {
    private Long id;
    private String regNumber;
    private String producer;
    private String model;
    private String type;
    private String condition;
    private Double rentalPrice;
    private Double purchaseAmount;
    private Date dateOfPurchase;
    private Boolean inUse;

    public Bike() {}

    public Bike(Long id, String regNumber, String producer, String model, String type, String condition, Double rentalPrice, Double purchaseAmount, Date dateOfPurchase, Boolean inUse) {
        this.id = id;
        this.regNumber = regNumber;
        this.producer = producer;
        this.model = model;
        this.type = type;
        this.condition = condition;
        this.rentalPrice = rentalPrice;
        this.purchaseAmount = purchaseAmount;
        this.dateOfPurchase = dateOfPurchase;
        this.inUse = inUse;
    }

    public Bike(String regNumber, String producer, String model, String type, String condition, Double rentalPrice, Double purchaseAmount, Date dateOfPurchase, Boolean inUse) {
        this.regNumber = regNumber;
        this.producer = producer;
        this.model = model;
        this.type = type;
        this.condition = condition;
        this.rentalPrice = rentalPrice;
        this.purchaseAmount = purchaseAmount;
        this.dateOfPurchase = dateOfPurchase;
        this.inUse = inUse;
    }

    public Long getId() {
        return id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(id, bike.id) &&
                Objects.equals(regNumber, bike.regNumber) &&
                Objects.equals(producer, bike.producer) &&
                Objects.equals(model, bike.model) &&
                Objects.equals(type, bike.type) &&
                Objects.equals(condition, bike.condition) &&
                Objects.equals(rentalPrice, bike.rentalPrice) &&
                Objects.equals(purchaseAmount, bike.purchaseAmount) &&
                Objects.equals(dateOfPurchase, bike.dateOfPurchase) &&
                Objects.equals(inUse, bike.inUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regNumber, producer, model, type, condition, rentalPrice, purchaseAmount, dateOfPurchase, inUse);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", regNumber='" + regNumber + '\'' +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", condition='" + condition + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", purchaseAmount=" + purchaseAmount +
                ", dateOfPurchase=" + dateOfPurchase +
                ", inUse=" + inUse +
                '}';
    }
}
