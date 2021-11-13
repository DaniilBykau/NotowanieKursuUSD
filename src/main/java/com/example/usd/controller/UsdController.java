package com.example.usd.controller;

import com.example.usd.service.UsdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usd/")
public class UsdController {

    @Autowired
    private UsdService rateService ;

    @Autowired
    public UsdController(UsdService rateService) {
        this.rateService = rateService;
    }

    //pobieranie dat
    @GetMapping("dates/{Date}")
    public List<String> getDateHistory(@PathVariable(value = "Date") String date) {
        return rateService.getDates(date);
    }

    //pobieranie historii kursu sprzedaży
    @GetMapping("asks/{Date}")
    public List<Float> getAsksHistory(@PathVariable(value = "Date") String date) {
        return rateService.getAsks(date);
    }

    //pobieranie historii kursu kupna
    @GetMapping("bids/{Date}")
    public List<Float> getBidHistory(@PathVariable(value = "Date") String date) {
        return rateService.getBids(date);
    }

    //pobieranie różnicy kupna
    @GetMapping("bids/difference/{Date}")
    public Float getBidDifference(@PathVariable(value = "Date") String date) {
        return rateService.getBidsDifference(date);
    }

    //pobieranie różnicy sprzedaży
    @GetMapping("asks/difference/{Date}")
    public Float getAskDifference(@PathVariable(value = "Date") String date) {
        return rateService.getAsksDifference(date);
    }
}
