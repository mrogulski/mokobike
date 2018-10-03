package com.mokobike.exceptions;

public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private long id;

    public NotFoundException(long id) {
        this.id = id;
    }

    public long getId(){
        return id;
    }
}
