package com.mokobike.error;

import com.mokobike.exceptions.NoBikeAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class NoBikesAvailableError {

    @ExceptionHandler(NoBikeAvailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error noBikesAvailable(NoBikeAvailableException e){
        String dateFrom = e.getDateFrom();
        String dateTo = e.getDateTo();
        Integer availableAdultBikes = e.getAvailableAdultBikes();
        Integer availableChildBikes = e.getAvailableChildBikes();

        return new Error(409, "No available bikes in " + dateFrom + " - " + dateTo + ". Available bikes in this period: adults: " + availableAdultBikes + ", childs:" + availableChildBikes);
    }
}
