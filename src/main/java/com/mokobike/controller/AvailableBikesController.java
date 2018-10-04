package com.mokobike.controller;

import com.mokobike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AvailableBikesController extends Controller {

    @Autowired
    BikeRepository bikeRepository;

    @GetMapping
    @RequestMapping(value = "/bikes/availability")
    @ResponseBody
    public Integer checkAvailableBikes(
            @RequestParam(name="dateFrom")Date dateFrom,
            @RequestParam(name="dateTo")Date dateTo,
            @RequestParam(name="type")String type
    ){

        return bikeRepository.findAvailableBikes(dateFrom, dateTo,type);
    }

}
