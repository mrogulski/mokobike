package com.mokobike.controller;

import com.mokobike.domain.Bike;
import com.mokobike.domain.Order;
import com.mokobike.exceptions.NotFoundException;
import com.mokobike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value = "/bikes")
public class BikeController extends Controller{

    @Autowired
    BikeRepository bikeRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ResponseBody
    public List<Object> getBikes(
            @RequestParam(name="page") int page,
            @RequestParam(name="size") int size
    ){
        List<Object> response = new ArrayList<>();
        Map<String, Integer> count = new HashMap<>();
        List<Bike> bikes;
        bikes = bikeRepository.findAllBikes(page, size);
        count.put("total_bikes", bikeRepository.bikesCount());

        response.add(count);
        response.add(bikes);

        return response;
    }

    @PostMapping
    @ResponseBody
    public Long save(@RequestBody Bike bike){
        Long bikeID;
        bikeRepository.save(bike);
        bikeID = bikeRepository.findLatestBike().getId();

        return bikeID;
    }

    @GetMapping(value = "/{bike_id}")
    @ResponseBody
    public Bike getById( @PathVariable("bike_id") long bikeID){
        Bike bike = bikeRepository.findByID(bikeID);
        if(bike == null){
            throw new NotFoundException(bikeID);
        }
        logger.info("someone is asking about bike number " + bikeID);
        return bike;
    }

    @DeleteMapping(value = "/{bike_id}" )
    public void deleteBike(@PathVariable("bike_id") long bikeID){
        Bike bike = bikeRepository.findByID(bikeID);
        if(bike == null){
            throw new NotFoundException(bikeID);
        }else{
            bikeRepository.delete(bikeID);
        }

        logger.info("someone trying to delete bike number " + bikeID);
    }


    @PatchMapping(value = "/{bike_id}")
    @ResponseBody
    public Bike updateBike(@PathVariable("bike_id") long bikeID, @RequestBody Bike bike){
        bike.setId(bikeID);
        Bike updatedBike = bikeRepository.update(bike);
        if(updatedBike == null){
            throw new NotFoundException(bikeID);
        }
        return updatedBike;
    }
}
