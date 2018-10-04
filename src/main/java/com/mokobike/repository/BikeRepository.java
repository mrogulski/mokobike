package com.mokobike.repository;

import com.mokobike.domain.Bike;

import java.util.List;

public interface BikeRepository {

    Bike findByID(Long ID);

    List<Bike> findAllBikes(int page, int size);

    List<Bike> findAllBikes();

    int bikesCount();

    void save(Bike bike);

    Bike update(Bike bike);

    void delete(Long bikeID);

    Bike findLatestBike();
}
