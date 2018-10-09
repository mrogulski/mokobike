package com.mokobike.controller;

import com.mokobike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;


@RestController
public class AvailableBikesController extends Controller {

    @Autowired
    BikeRepository bikeRepository;

    @GetMapping
    @RequestMapping(value = "/bikes/availability")
    @ResponseBody
    public HashMap<String, Integer> checkAvailableBikes(
            @RequestParam(name="dateFrom") Long dateFrom,
            @RequestParam(name="dateTo") Long dateTo,
            @RequestParam(name="type")String type
    ){
        SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        fromat.setTimeZone(TimeZone.getTimeZone("Europe/Sarajevo"));

        HashMap<String, Integer> availableBikes = new HashMap<String, Integer>();

        String from = fromat.format(dateFrom);
        String to = fromat.format(dateTo);

        availableBikes.put(type.toLowerCase() + "_bikes", bikeRepository.findAvailableBikes(from, to, type));

        return availableBikes;

    }

}
