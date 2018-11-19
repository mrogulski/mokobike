package com.mokobike.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private String status;
    private Date createdDate;
    private Date dateFrom;
    private Date dateTo;
    private Integer user;
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

}
