package com.mokobike.error;

import com.mokobike.exceptions.order.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class OrderError {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error orderNotFound(OrderNotFoundException e){
        long orderId = e.getOrderId();
        return new Error(404, "Order " + orderId + " not FOUND");
    }
}
