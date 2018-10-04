package com.mokobike.repository;

import com.mokobike.domain.Bike;
import org.omg.CORBA.INTERNAL;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BikeRepository {

    Bike findByID(Long ID);

    List<Bike> findAllBikes(int page, int size);

    List<Bike> findAllBikes();

    int bikesCount();

    void save(Bike bike);

    Bike update(Bike bike);

    void delete(Long bikeID);

    Bike findLatestBike();

   Integer findAvailableBikes(Date dateFrom, Date dateTo, String type);

}
