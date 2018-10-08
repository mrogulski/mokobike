package com.mokobike.controller;

import com.mokobike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;


@RestController
public class AvailableBikesController extends Controller {

    @Autowired
    BikeRepository bikeRepository;

    @GetMapping
    @RequestMapping(value = "/bikes/availability")
    @ResponseBody
    public Integer checkAvailableBikes(
            @RequestParam(name="dateFrom") Long dateFrom,
            @RequestParam(name="dateTo") Long dateTo,
            @RequestParam(name="type")String type
    ){
        SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String from = fromat.format(dateFrom);
        String to = fromat.format(dateTo);

        return bikeRepository.findAvailableBikes(from, to, type);
    }

}
