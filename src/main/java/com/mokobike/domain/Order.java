package com.mokobike.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
public class Order {

    @Id
    private Long id;

    private Date createdDate;

    private Date orderDate;

    private String status;

    private Long userId;


}
