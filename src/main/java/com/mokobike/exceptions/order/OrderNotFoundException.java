package com.mokobike.exceptions.order;

public class OrderNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private long orderId;

    public OrderNotFoundException(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderId(){
        return orderId;
    }
}
