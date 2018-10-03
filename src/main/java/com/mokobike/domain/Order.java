package com.mokobike.domain;

import java.util.Date;
import java.util.Objects;


public class Order {

    private Long id;
    private String status;
    private Date createdDate;
    private Date dateFrom;
    private Date dateTo;
    private Long userId;
    private Integer adultBike;
    private Integer childBike;
    private Integer helmet;
    private Integer lock;
    private Boolean pickup;
    private String pickupFrom;
    private String pickupTo;
    private Long pickupDistance;
    private Long pickupValue;
    private Long initialValue;
    private Long finalValue;

    public Order(){}

    public Order(
            String status,
            Date createdDate,
            Date dateFrom,
            Date dateTo,
            Long userId,
            Integer adultBike,
            Integer childBike,
            Integer helmet,
            Integer lock,
            Boolean pickup,
            String pickupFrom,
            String pickupTo,
            Long pickupDistance,
            Long pickupValue,
            Long initialValue,
            Long finalValue
    ) {
        this.status = status;
        this.createdDate = createdDate;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
        this.adultBike = adultBike;
        this.childBike = childBike;
        this.helmet = helmet;
        this.lock = lock;
        this.pickup = pickup;
        this.pickupFrom = pickupFrom;
        this.pickupTo = pickupTo;
        this.pickupDistance = pickupDistance;
        this.pickupValue = pickupValue;
        this.initialValue = initialValue;
        this.finalValue = finalValue;
    }

    public Order(
            Long id,
            String status,
            Date createdDate,
            Date dateFrom,
            Date dateTo,
            Long userId,
            Integer adultBike,
            Integer childBike,
            Integer helmet,
            Integer lock,
            Boolean pickup,
            String pickupFrom,
            String pickupTo,
            Long pickupDistance,
            Long pickupValue,
            Long initialValue,
            Long finalValue

    ){
        this.id = id;
        this.status = status;
        this.createdDate = createdDate;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
        this.adultBike = adultBike;
        this.childBike = childBike;
        this.helmet = helmet;
        this.lock = lock;
        this.pickup = pickup;
        this.pickupFrom = pickupFrom;
        this.pickupTo = pickupTo;
        this.pickupDistance = pickupDistance;
        this.pickupValue = pickupValue;
        this.initialValue = initialValue;
        this.finalValue = finalValue;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAdultBike() {
        return adultBike;
    }

    public void setAdultBike(Integer adultBike) {
        this.adultBike = adultBike;
    }

    public Integer getChildBike() {
        return childBike;
    }

    public void setChildBike(Integer childBike) {
        this.childBike = childBike;
    }

    public Integer getHelmet() {
        return helmet;
    }

    public void setHelmet(Integer helmet) {
        this.helmet = helmet;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPickup(Boolean pickup) {
        this.pickup = pickup;
    }

    public String getPickupFrom() {
        return pickupFrom;
    }

    public void setPickupFrom(String pickupFrom) {
        this.pickupFrom = pickupFrom;
    }

    public String getPickupTo() {
        return pickupTo;
    }

    public void setPickupTo(String pickupTo) {
        this.pickupTo = pickupTo;
    }

    public Long getPickupDistance() {
        return pickupDistance;
    }

    public void setPickupDistance(Long pickupDistance) {
        this.pickupDistance = pickupDistance;
    }

    public Long getPickupValue() {
        return pickupValue;
    }

    public void setPickupValue(Long pickupValue) {
        this.pickupValue = pickupValue;
    }

    public Long getInitialValue() {
        return initialValue;
    }

    public Long getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Long finalValue) {
        this.finalValue = finalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getStatus(), order.getStatus()) &&
                Objects.equals(getCreatedDate(), order.getCreatedDate()) &&
                Objects.equals(getDateFrom(), order.getDateFrom()) &&
                Objects.equals(getDateTo(), order.getDateTo()) &&
                Objects.equals(getUserId(), order.getUserId()) &&
                Objects.equals(getAdultBike(), order.getAdultBike()) &&
                Objects.equals(getChildBike(), order.getChildBike()) &&
                Objects.equals(getHelmet(), order.getHelmet()) &&
                Objects.equals(getLock(), order.getLock()) &&
                Objects.equals(getPickup(), order.getPickup()) &&
                Objects.equals(getPickupFrom(), order.getPickupFrom()) &&
                Objects.equals(getPickupTo(), order.getPickupTo()) &&
                Objects.equals(getPickupDistance(), order.getPickupDistance()) &&
                Objects.equals(getPickupValue(), order.getPickupValue()) &&
                Objects.equals(getInitialValue(), order.getInitialValue()) &&
                Objects.equals(getFinalValue(), order.getFinalValue());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getStatus(), getCreatedDate(), getDateFrom(), getDateTo(), getUserId(), getAdultBike(), getChildBike(), getHelmet(), getLock(), getPickup(), getPickupFrom(), getPickupTo(), getPickupDistance(), getPickupValue(), getInitialValue(), getFinalValue());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", userId=" + userId +
                ", adultBike=" + adultBike +
                ", childBike=" + childBike +
                ", helmet=" + helmet +
                ", lock=" + lock +
                ", pickup=" + pickup +
                ", pickupFrom='" + pickupFrom + '\'' +
                ", pickupTo='" + pickupTo + '\'' +
                ", pickupDistance=" + pickupDistance +
                ", pickupValue=" + pickupValue +
                ", initialValue=" + initialValue +
                ", finalValue=" + finalValue +
                '}';
    }
}
