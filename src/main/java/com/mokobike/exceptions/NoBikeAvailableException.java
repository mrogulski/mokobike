package com.mokobike.exceptions;

public class NoBikeAvailableException extends RuntimeException {

    String dateFrom;
    String dateTo;
    Integer availableAdultBikes;
    Integer availableChildBikes;

    public NoBikeAvailableException(String dateFrom, String dateTo, Integer availableAdultBikes, Integer availableChildBikes) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.availableAdultBikes = availableAdultBikes;
        this.availableChildBikes = availableChildBikes;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public Integer getAvailableAdultBikes() {
        return availableAdultBikes;
    }

    public Integer getAvailableChildBikes() {
        return availableChildBikes;
    }
}
