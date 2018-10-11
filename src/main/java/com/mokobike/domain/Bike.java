package com.mokobike.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
