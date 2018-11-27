package com.mokobike.controller;

import com.mokobike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;



@RestController
public class AvailableBikesController extends Controller {

    @Autowired
    BikeRepository bikeRepository;

    @GetMapping
    @RequestMapping(value = "/bikes/availability")
    @ResponseBody
    public HashMap<String, Integer> checkAvailableBikes(
            @RequestParam(name="dateFrom")  String dateFrom,
            @RequestParam(name="dateTo")  String  dateTo,
            @RequestParam(name="type")String type
    ){
        HashMap<String, Integer> availableBikes = new HashMap<>();
        availableBikes.put(type.toLowerCase() + "_bikes", bikeRepository.findAvailableBikes(dateFrom, dateTo, type));

        return availableBikes;

    }

}
